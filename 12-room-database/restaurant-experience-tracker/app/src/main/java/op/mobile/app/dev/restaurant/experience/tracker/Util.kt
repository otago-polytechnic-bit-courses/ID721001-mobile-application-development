package op.mobile.app.dev.restaurant.experience.tracker

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat

fun convertNumericRatingToString(rating: Int): String {
    var qualityString = ""
    when (rating) {
        -1 -> qualityString = "N/A"
        1 -> qualityString = "One"
        2 -> qualityString = "Two"
        3 -> qualityString = "Three"
    }
    return qualityString
}

@SuppressLint("SimpleDateFormat", "WeekBasedYear")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("DD-MM-YY' at 'hh:mm a").format(systemTime).toString()
}

fun formatRestaurantData(data: List<Restaurant>): Spanned {
    val stringBuilder = StringBuilder()
    stringBuilder.apply {
        data.forEach {
            append("<br>")
            append("<b>Start: </b>")
            append(convertLongToDateString(it.startTimeMilli))
            append("<br>")
            if (it.endTimeMilli != it.startTimeMilli) {
                append("<b>End: </b>")
                append(convertLongToDateString(it.endTimeMilli))
                append("<br>")
                append("<b>Time Spent: </b>")
                append(
                        "${it.endTimeMilli.minus(it.startTimeMilli) / 1000 / 60 / 60} hours, ${
                            it.endTimeMilli.minus(
                                    it.startTimeMilli
                            ) / 1000 / 60
                        } minutes and ${it.endTimeMilli.minus(it.startTimeMilli) / 1000} seconds"
                )
                append("<br>")
                append("<b>Rating: </b>")
                append(convertNumericRatingToString(it.restaurantRating))
                append("<br>")
            }
        }
    }
    return Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY)
}