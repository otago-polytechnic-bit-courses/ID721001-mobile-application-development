# **13: Google Maps**

In today's session we are going to implement **Google Maps** into our **Travelling** application.

## Code Example

Lets take a look at the code example. Open the `google-maps-example` in **Android Studio**. Make sure you have the following dependency in your `build.gradle (Module)`:

```
implementation 'com.google.android.gms:play-services-maps:18.0.2'
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'com.google.maps.android:android-maps-utils:0.5'
```

Also, in the `android` block, add the following under `testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"`:

```
def secretProperties = new Properties()
if (rootProject.file("local.properties").exists()) {
    rootProject.file("local.properties")?.withInputStream {
        secretProperties.load(it)
    }
}
resValue "string", "google_maps_key", (secretProperties["GOOGLE_MAPS_KEY"] ?: "")
```

This is used to access various credentials from `local.properties` that you do not want to expose publicly, i.e., API keys.

In `local.properties`, add the following:

```
GOOGLE_MAPS_KEY=<API KEY>
```

The `API KEY` is located in course channel on **Microsoft Teams**, under the **Files** tab > `goggle-maps-key.txt`.

### Model

In the `model` directory, create a new file called `Attraction.kt`. In this file, add the following:

```kt
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Attraction(
    val name: String,
    val cityTown: String,
    val location: @RawValue Location
) : Parcelable

data class Location(
    val latitude: Double,
    val longitude: Double
)
```

In `Country.kt`, add the following argument to the class constructor

```kt
val attractions: @RawValue List<Attraction>, // Serialize List of Attraction
```

If you refer back to your **GitHub Gist**, this should resemble the **JSON** structure/shape.

### Fragment & Layout

In `res` > `layout`, create two new layout files called `fragment_map.xml` and `marker_info_window.xml`. In `fragment_map.xml`, add the following code:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

In `marker_info_window.xml`, add the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/marker_info_window_rectangle">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="16dp"
            android:layout_marginTop="4dp"
            android:layout_marginEnd="16dp"
            android:gravity="center"
            android:textColor="@color/black"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

You will notice that we have not created `marker_info_window_rectangle.xml` in the `drawable` package. Lets create that.

In the `drawable` package, create a new file called `marker_info_window_rectangle.xml` and add the following:

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/white" />
    <padding
        android:bottom="5dp"
        android:left="5dp"
        android:right="5dp"
        android:top="5dp" />
    <corners android:radius="8dp" />
</shape>
```

This will style each marker's info window. More about this soon.

In the `ui` package, create a new package called `map`. In this directory create two new files called `MapFragment.kt` and `AttractionInfoWindow.kt`.

In `AttractionInfoWindow.kt`, add the following:

```kt
/**
 * Class implements GoogleMap.InfoWindowAdapter. This class needs a reference
 * to a Context, i.e., MapFragment
 */
class AttractionInfoWindow(context: Context) : GoogleMap.InfoWindowAdapter {

    /**
     * Inflating the marker_info_window.xml
     */
    private val binding: MarkerInfoWindowBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.marker_info_window,
        null,
        false
    )

    /**
     * Implementing member of GoogleMap.InfoWindowAdapter. Provides a 
     * custom info window for a marker
     *
     * More information here - https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.InfoWindowAdapter#getInfoWindow
     */
    override fun getInfoWindow(marker: Marker): View {
        val attraction: Attraction = marker.tag as Attraction
        binding.tvName.text = attraction.name
        return binding.root
    }

    /**
     * Implementing member of GoogleMap.InfoWindowAdapter. We are not concerned
     * with this. However, it provides custom content for the default info window
     * frame of a marker
     *
     * More information here - https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.InfoWindowAdapter#getInfoContents
     */
    override fun getInfoContents(marker: Marker): View? = null
}
```

In `MapFragment.kt`, add the following:

```kt
/**
 * Class implements OnMapReadyCallback
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    /**
     * Get List of Attraction from the HomeFragment
     */
    private val attractions by lazy {
        MapFragmentArgs.fromBundle(requireArguments()).attractions.toList()
    }

    /**
     * Inflating the fragment_map.xml
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Get the fragment widget in fragment_map.xml
         */
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment

        /**
         * A Google Map must be acquired using getMapAsync(OnMapReadyCallback). It
         * will automatically initialise the Map's system and view
         */
        mapFragment?.getMapAsync(this)
    }

    /**
     * Implementing member of OnMapReadyCallback. It needs a reference to a GoogleMap
     */
    override fun onMapReady(googleMap: GoogleMap) {
        val defaultZoom = 5f
        /**
         * For each Attraction, create a marker and add it to the Google Map
         */
        attractions.forEach {
            val marker = MarkerOptions() // Create a new MarkerOption object
            marker.position(LatLng(it.location.latitude, it.location.longitude)) // Coordinates of the marker
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)) // Colour of the marker
            googleMap.addMarker(marker)?.tag = it 
        }

        /**
         * Set the info window adapter. This info is set in AttractionInfoWindow, i.e., setting
         * a TextView widget's text property to the Attraction's name
         */
        googleMap.setInfoWindowAdapter(AttractionInfoWindow(requireContext()))

        /**
         * Get the initial position of the first Attraction's 
         * latitude and longitude coordinates
         */
        val initialPosition = LatLng(
            attractions[0].location.latitude,
            attractions[0].location.longitude
        )

        /**
         * Set the Google Map's camera position to the first Attraction's
         * latitude and longitude coordinates
         */ 
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, defaultZoom))
    }
}
```
