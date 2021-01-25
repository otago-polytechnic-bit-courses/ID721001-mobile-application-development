package op.mobile.dev.tech.companies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
}