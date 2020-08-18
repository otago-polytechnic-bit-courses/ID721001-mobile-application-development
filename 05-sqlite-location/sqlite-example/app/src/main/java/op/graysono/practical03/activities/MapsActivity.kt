package op.graysono.practical03.activities

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import op.graysono.practical03.R
import op.graysono.practical03.helpers.MapsData

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapsActivity)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val data: ArrayList<MapsData> = arrayListOf(
            MapsData("Sunset Sound", LatLng(34.0977818, -118.3349175)),
            MapsData("Mix Recording Studio", LatLng(34.0628235, -118.2835217)),
            MapsData("Silvelake Recording Studio", LatLng(34.0898534, -118.2838647)),
            MapsData("Blue West Recording Studios", LatLng(34.1019772, -118.3327732))
        )

        val zoomLevel = 15f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data[1].location, zoomLevel))

        for (d: MapsData in data) {
            map.addMarker(
                MarkerOptions().position(d.location).title(d.name)
            )
        }

        setMapLongClick(map)
        setPointOfInterest(map)
        setMapStyle(map)
        enableMyLocation()
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            val snippet: String =
                "Latitude: " + latLng.latitude + ", " + "Longitude: " + latLng.longitude
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    private fun setPointOfInterest(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker: Marker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )
            poiMarker.showInfoWindow()
        }
    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            val success: Boolean = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            if (!success) {
                Toast.makeText(this@MapsActivity, "Error loading map style", Toast.LENGTH_LONG)
                    .show()
            }
        } catch (e: Resources.NotFoundException) {
            Toast.makeText(this@MapsActivity, "Map style not found", Toast.LENGTH_LONG).show()
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@MapsActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 1
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this@MapsActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }
}