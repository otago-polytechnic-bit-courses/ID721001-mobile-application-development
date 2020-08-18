package op.graysono.practical03.helpers

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    var name: String?,
    var image: String?,
    var playCount: Int,
    var url: String?
) : Parcelable