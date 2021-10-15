package op.mobile.app.dev.bottom.navigation.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.bottom.navigation.R
import op.mobile.app.dev.bottom.navigation.helpers.Company
import op.mobile.app.dev.bottom.navigation.helpers.CompanyJSONReader
import op.mobile.app.dev.bottom.navigation.helpers.CompanyMarkerCluster

class MapsFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val companies = CompanyJSONReader(requireContext()).read()
        val clusterManager: ClusterManager<Company> = ClusterManager(requireContext(), googleMap)
        val markerCluster = CompanyMarkerCluster(requireContext(), googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }
}