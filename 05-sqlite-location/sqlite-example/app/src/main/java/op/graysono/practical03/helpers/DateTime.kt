package op.graysono.practical03.helpers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    fun currentDateTime(): String {
        val date: Date = Calendar.getInstance().time
        val outputPattern = "dd/MM/yyyy kk:mm:ss"
        val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
        return outputFormat.format(date)
    }

    fun formatDateTime(dateTime: String): String {
        val inputPattern = "dd/MM/yyyy kk:mm:ss"
        val outputPattern = "dd MMM yyyy hh:mm:ss a"

        val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)

        lateinit var date: Date
        lateinit var str: String

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}

