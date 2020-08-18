package graysono.com.cp16notifications.helpers

import android.content.SharedPreferences

fun SharedPreferences.Editor.putDouble(key: String, double: Double): SharedPreferences.Editor =
    putLong(key, java.lang.Double.doubleToRawLongBits(double))

fun SharedPreferences.getDouble(key: String, default: Double): Double =
    java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))

fun String.pluralize(count: Int): String? {
    return if (count > 1) {
        this + 's'
    } else {
        this
    }
}