# **Google Maps**

# **Google Maps**



```kotlin
...

data class Company(val latLng: LatLng, val name: String, val city: String) : ClusterItem {
    override fun getPosition(): LatLng = latLng
    override fun getTitle(): String = name
    override fun getSnippet(): String = ""
}
```


```kotlin
...

class CompanyJSONReader(private val context: Context) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.data)

    fun read(): List<Company> {
        val itemType = object : TypeToken<List<CompanyJSONResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson<List<CompanyJSONResponse>>(reader, itemType).map {
            it.toCompany()
        }
    }
}
```

```kotlin
...

data class CompanyJSONResponse(val name: String, val city: String, val location: Location) {
    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}

fun CompanyJSONResponse.toCompany() = Company(
    name = name,
    city = city,
    latLng = LatLng(location.latitude, location.longitude)
)
```

```kotlin
...

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
        val drawable: Drawable? = ContextCompat.getDrawable(ctx, android.R.color.transparent)
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
```

```kotlin
...

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
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }
}
```
