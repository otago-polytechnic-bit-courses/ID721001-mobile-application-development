package op.mobile.app.dev.bottom.navigation.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
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
import op.mobile.app.dev.bottom.navigation.R
import op.mobile.app.dev.bottom.navigation.databinding.ClusterMarkerBinding
import op.mobile.app.dev.bottom.navigation.databinding.ClusterMarkerInfoWindowBinding

class CompanyMarkerCluster(
    context: Context,
    private val map: GoogleMap,
    clusterManager: ClusterManager<Company>
) :
    DefaultClusterRenderer<Company>(context, map, clusterManager),
    OnClusterClickListener<Company>,
    OnInfoWindowClickListener {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val iconGen = IconGenerator(context)
    private var binding: ClusterMarkerBinding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.cluster_marker,
        null,
        false
    )

    init {
        val drawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.transparent)
        iconGen.setBackground(drawable)
        iconGen.setContentView(binding.root)

        clusterManager.setOnClusterClickListener(this)
        clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindowAdapter())

        map.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnInfoWindowClickListener(this)
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
        binding.tvCluster.text = cluster.size.toString()

        val icon: Bitmap = iconGen.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun onClusterItemRendered(clusterItem: Company, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun onClusterClick(cluster: Cluster<Company>): Boolean {
        val builder = LatLngBounds.Builder()
        for (data: Company in cluster.items)
            builder.include(data.position)

        try {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onInfoWindowClick(marker: Marker) {}

    inner class InfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val binding: ClusterMarkerInfoWindowBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.cluster_marker_info_window,
            null,
            false
        )

        override fun getInfoWindow(marker: Marker): View {
            val company: Company = marker.tag as Company
            binding.tvName.text = company.name
            binding.tvCity.text = company.city
            return binding.root
        }

        override fun getInfoContents(marker: Marker): View? {
            return null
        }
    }
}