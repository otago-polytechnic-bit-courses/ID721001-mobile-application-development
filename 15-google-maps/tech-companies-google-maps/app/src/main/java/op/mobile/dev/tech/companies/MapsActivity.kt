package op.mobile.dev.tech.companies

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var toolbar: Toolbar
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<Company>
    private lateinit var markerCluster: CompanyMarkerCluster
    private lateinit var companies: List<Company>
    private lateinit var sharedPref: SharedPreferences
    private var isMapStyleChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        isMapStyleChange = sharedPref.getBoolean(getString(R.string.map_style_key), false)
        setMapStyle(map)
        companies = CompanyJSONReader(this).read()
        clusterManager = ClusterManager(this, map)
        markerCluster = CompanyMarkerCluster(this, map, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map_style, menu)
        return true
    }

    private fun setMapStyle(map: GoogleMap) {
        val isSet: Boolean =
            when (sharedPref.getBoolean(getString(R.string.map_style_key), false)) {
                false -> map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this,
                        R.raw.map_style_light
                    )
                )
                else -> map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this,
                        R.raw.map_style_dark
                    )
                )
            }

        if (!isSet) Toast.makeText(
            this,
            getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_map_style -> {
                isMapStyleChange = if (isMapStyleChange) {
                    map.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                            this,
                            R.raw.map_style_light
                        )
                    )
                    false
                } else {
                    map.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                            this,
                            R.raw.map_style_dark
                        )
                    )
                    true
                }
                sharedPref.edit().putBoolean(getString(R.string.map_style_key), isMapStyleChange)
                    .apply()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}