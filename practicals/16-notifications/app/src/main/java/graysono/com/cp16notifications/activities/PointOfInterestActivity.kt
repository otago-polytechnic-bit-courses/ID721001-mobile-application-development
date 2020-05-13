package graysono.com.cp16notifications.activities

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import graysono.com.cp16notifications.R
import graysono.com.cp16notifications.custom.CustomToast
import graysono.com.cp16notifications.helpers.*

class PointOfInterestActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var map: GoogleMap
    private lateinit var fusedLocClient: FusedLocationProviderClient
    private lateinit var locReq: LocationRequest
    private lateinit var locCallback: LocationCallback
    private lateinit var clusterManager: ClusterManager<PointsOfInterest>
    private lateinit var markerCluster: MarkerCluster
    private lateinit var customToast: CustomToast
    private lateinit var pois: ArrayList<PointsOfInterest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recording_studio)
        displayToolbar(isHomeEnabled = false, isTitleEnabled = true)
        val mapFragment: SupportMapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this@PointOfInterestActivity)
        fusedLocClient = LocationServices.getFusedLocationProviderClient(this@PointOfInterestActivity)
        locReq = LocationRequest()
        locCallback = LocationCallback()
        customToast = CustomToast(this@PointOfInterestActivity)
        getLastLocation()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        pois = arrayListOf(
            PointsOfInterest("Burger King", LatLng(-45.8937627, 170.5035882)),
            PointsOfInterest("Cash Converters", LatLng(-45.8939491, 170.4990944))
            // Add four more points of interest. Make sure they are within 500km of your current location
        )

        clusterManager = ClusterManager(this@PointOfInterestActivity, map)
        markerCluster = MarkerCluster(this@PointOfInterestActivity, map, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(pois)
        clusterManager.cluster()

        val zoomLevel = 15f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pois[0].position, zoomLevel))
        map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this@PointOfInterestActivity,
                R.raw.map_dark_style
            )
        )
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        enableMyLocation()
    }

    private fun getPOIsCount(userLocLat: Double, userLocLng: Double, data: ArrayList<PointsOfInterest>): Int {
        // Declare an integer variable called count. Give it a default value of 0
        // Loop through each PointsOfInterest in the ArrayList of PointsOfInterest
            // Check if the marker's distance is less than 500km. Call the getDistance method in MakerCluster
                // Increment the count by 1
        // Return the count
    }

    private fun isPermissionGranted(): Boolean {
        // Check of ACCESS_FINE_LOCATION has been granted by the user
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
            // Call showNotification method
        } else {
            // If permissions have not been previously granted, request permissions again
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                // Call enabledMyLocation method
            }
        }
    }

    private fun requestNewLocationData() {
        locReq.interval = 1
        locReq.fastestInterval = 1
        locReq.smallestDisplacement = 50F
        locReq.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                if (locationResult.locations.isNotEmpty()) {
                    val lastLoc: Location = locationResult.lastLocation
                    sharedPref.edit().putDouble("recording_lat", lastLoc.latitude).apply()
                    sharedPref.edit().putDouble("recording_lng", lastLoc.longitude).apply()
                }
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun showNotification() {
        val userLocLat: Double = sharedPref.getDouble("recording_lat", 0.0)
        val userLocLng: Double = sharedPref.getDouble("recording_lng", 0.0)
        val poisCount: Int = getPOIsCount(userLocLat, userLocLng, pois)

        // When a user receives a notification, if the user clicks on the notification, they will
        // be return to the application via an intent. This intent will be pending until the
        // user has clicked the notification
        val resultIntent = Intent(this@PointOfInterestActivity, PointOfInterestActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // Resources if you get stuck:
        // * https://developer.android.com/training/notify-user/build-notification#click
        // * https://developer.android.com/training/notify-user/build-notification

        // Check if the poisCount is greater than 0
            // Build a notification using NotificationCompat.Builder
            // Your notification must have set:
            // * setContentIntent - pass in resultPendingIntent
            // * setContentTitle - display the notification title
            // * setSmallIcon - display the notification icon
            // * setContentText - display the number of pois in your area
            // * setPriority - pass in NotificationCompat.PRIORITY_DEFAULT
            // * setAutoCancel - pass in true
            // Show the notification. If you get stuck, refer to this link -
        // else
            // Display a custom toast with the following message: "There are no points of interest in your area."
    }

    private fun getLastLocation() {
        if (isPermissionGranted()) {
            if (isLocationEnabled()) {
                requestNewLocationData()
            } else {
                customToast.show("Location must be turned on.")
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            enableMyLocation()
        }
    }

    private fun startLocationUpdates() {
        fusedLocClient.requestLocationUpdates(
            locReq,
            locCallback,
            null
        )
    }

    private fun stopLocationUpdates() {
        fusedLocClient.removeLocationUpdates(locCallback)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
        private const val CHANNEL_ID = "graysono.com.cp16notifications"
    }
}

