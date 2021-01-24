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

    private lateinit var toolbar: Toolbar
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<Company>
    private lateinit var markerCluster: CompanyMarkerCluster
    private lateinit var companies: List<Company>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        companies = CompanyJSONReader(this).read()
        clusterManager = ClusterManager(this, map)
        markerCluster = CompanyMarkerCluster(this, map, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }
}