package graysono.com.cp16notifications.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener
import com.google.maps.android.ui.IconGenerator
import graysono.com.cp16notifications.R

class MarkerCluster(
    private val context: Context,
    private val map: GoogleMap,
    clusterManager: ClusterManager<PointsOfInterest>
) :
    DefaultClusterRenderer<PointsOfInterest>(context, map, clusterManager),
    OnClusterClickListener<PointsOfInterest>,
    OnInfoWindowClickListener {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val iconGen = IconGenerator(context)
    private val item: View = layoutInflater.inflate(R.layout.custom_single_marker, null)

    init {
        val drawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.transparent)
        iconGen.setBackground(drawable)
        iconGen.setContentView(item)
        clusterManager.setOnClusterClickListener(this)
        map.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnInfoWindowClickListener(this)
        clusterManager.markerCollection.setOnInfoWindowAdapter(CustomInfoWindowAdapter())
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
    }

    fun getDistance(userLocLat: Double, userLocLng: Double, position: LatLng): Double {
        return (SphericalUtil.computeDistanceBetween(LatLng(userLocLat, userLocLng), position)) / 1000
    }

    override fun onBeforeClusterItemRendered(item: PointsOfInterest, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<PointsOfInterest>,
        markerOptions: MarkerOptions
    ) {
        val txvCluster: TextView = item.findViewById(R.id.txvCluster)
        txvCluster.text = cluster.size.toString()

        val icon: Bitmap = iconGen.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun onClusterItemRendered(clusterItem: PointsOfInterest, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun onClusterClick(cluster: Cluster<PointsOfInterest>): Boolean {
        val builder = LatLngBounds.Builder()
        for (data: PointsOfInterest in cluster.items)
            builder.include(data.position)

        try {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onInfoWindowClick(marker: Marker) {}

    inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val item: View = layoutInflater.inflate(R.layout.custom_single_marker_info, null)

        override fun getInfoWindow(marker: Marker): View {
            val recordingStudio: PointsOfInterest = marker.tag as PointsOfInterest
            val txvItemName: TextView = item.findViewById(R.id.txvItemName)
            val txvItemLatLng: TextView = item.findViewById(R.id.txvItemLatLng)
            txvItemName.text = "Name: " + marker.title
            txvItemLatLng.text = "Website: " + recordingStudio.position
            return item
        }

        override fun getInfoContents(marker: Marker): View? {
            return null
        }
    }
}