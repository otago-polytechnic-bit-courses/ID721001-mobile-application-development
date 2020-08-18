package graysono.com.cp16notifications.helpers

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class PointsOfInterest(
    var name: String,
    var latLng: LatLng
) : ClusterItem {

    override fun getSnippet(): String {
        return ""
    }

    override fun getTitle(): String {
        return name
    }

    override fun getPosition(): LatLng {
        return latLng
    }
}