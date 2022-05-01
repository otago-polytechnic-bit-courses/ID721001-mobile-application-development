package op.mobile.app.dev.graysono.travelling.model

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