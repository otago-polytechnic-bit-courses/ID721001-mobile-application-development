package op.mobile.app.dev.companies

import com.google.android.gms.maps.model.LatLng

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