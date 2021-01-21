package op.mobile.dev.google.maps.companies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator

class MarkerCluster(
    ctx: Context,
    private val map: GoogleMap,
    clusterManager: ClusterManager<Company>
) :
    DefaultClusterRenderer<Company>(ctx, map, clusterManager),
    OnClusterClickListener<Company>,
    OnInfoWindowClickListener {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
    private val iconGen = IconGenerator(ctx)
    private val item: View = layoutInflater.inflate(R.layout.cluster_marker, null)

    init {
        val drawable: Drawable? = ContextCompat.getDrawable(ctx, android.R.color.transparent)
        iconGen.setBackground(drawable)
        iconGen.setContentView(item)

        clusterManager.setOnClusterClickListener(this)

        map.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnInfoWindowClickListener(this)

        clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindowAdapter())

        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
    }

    override fun onBeforeClusterItemRendered(item: Company, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<Company>,
        markerOptions: MarkerOptions
    ) {
        val txvCluster: TextView = item.findViewById(R.id.txt_view_cluster)
        txvCluster.text = cluster.size.toString()

        val icon: Bitmap = iconGen.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun onClusterItemRendered(clusterItem: Company, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun onClusterClick(cluster: Cluster<Company>): Boolean {
        val builder = LatLngBounds.Builder()
        for (data: Company in cluster.items) builder.include(data.position)

        try {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onInfoWindowClick(marker: Marker) {}

    inner class InfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val item: View = layoutInflater.inflate(R.layout.marker_info_window, null)

        override fun getInfoWindow(marker: Marker): View {
            val company: Company = marker.tag as Company
            val txtViewName: TextView = item.findViewById(R.id.txt_view_name)
            val txtViewCity: TextView = item.findViewById(R.id.txt_view_city)
            txtViewName.text = company.name
            txtViewCity.text = company.city
            return item
        }

        override fun getInfoContents(marker: Marker): View? {
            return null
        }
    }
}