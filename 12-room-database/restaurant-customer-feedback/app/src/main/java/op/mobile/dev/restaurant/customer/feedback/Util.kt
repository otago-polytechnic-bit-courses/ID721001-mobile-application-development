package op.mobile.dev.restaurant.customer.feedback

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat

fun convertNumericRatingToString(rating: Int, resources: Resources): String {
    var qualityString = ""
    when (rating) {
        -1 -> qualityString = "N/A"
        1 -> qualityString = "One Star"
        2 -> qualityString = "Two Stars"
        3 -> qualityString = "Three Stars"
    }
    return qualityString
}

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("MM-DD-yyyy' at 'hh:mm a").format(systemTime).toString()
}

fun formatRestaurantData(data: List<Restaurant>, resources: Resources): Spanned {
    val stringBuilder = StringBuilder()
    stringBuilder.apply {
        append("<h3 style=\"text-align:center\">Restaurant Experience History</h3>")
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
                append(convertNumericRatingToString(it.restaurantRating, resources))
                append("<br>")
            }
        }
    }
    return Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY)
}