package op.mobile.dev.google.maps.companies

import com.google.android.gms.maps.model.LatLng

data class CompanyJSONResponse(val loc: Location, val name: String, val city: String) {
    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}

fun CompanyJSONResponse.toCompany() = Company(
    latLng = LatLng(loc.latitude, loc.longitude),
    name = name,
    city = city
)