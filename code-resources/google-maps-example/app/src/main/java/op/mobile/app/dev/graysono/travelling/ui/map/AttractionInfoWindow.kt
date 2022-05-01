package op.mobile.app.dev.graysono.travelling.ui.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import op.mobile.app.dev.graysono.travelling.R
import op.mobile.app.dev.graysono.travelling.databinding.MarkerInfoWindowBinding
import op.mobile.app.dev.graysono.travelling.model.Attraction

/**
 * Class implements GoogleMap.InfoWindowAdapter. This class needs a reference
 * to a Context, i.e., MapFragment
 */
class AttractionInfoWindow(context: Context) : GoogleMap.InfoWindowAdapter {
    /**
     * Inflating the marker_info_window.xml. We will create this soon
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
