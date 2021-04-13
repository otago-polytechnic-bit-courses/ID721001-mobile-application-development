package op.mobile.app.dev.companies

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val companies = CompanyJSONReader(this).read()
        val clusterManager: ClusterManager<Company> = ClusterManager(this, googleMap)
        val markerCluster = CompanyMarkerCluster(this, googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map_style, menu)
        return true
    }
}