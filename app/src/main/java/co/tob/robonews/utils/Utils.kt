package co.tob.robonews.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun DateToTimeFormat(oldstringDate: String): String? {
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val date = sdf.parse(oldstringDate)
            val sdfTime = SimpleDateFormat("hh:mm a'", Locale.ENGLISH)
            isTime = sdfTime.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    fun DateFormat(oldstringDate: String?): String {
        val newDate: String
        val dateFormat = SimpleDateFormat(
            "E, d MMM yyyy",
            Locale(country)
        )
        newDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldstringDate.toString()
        }
        return newDate
    }

    val country: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.country.toString()
            return country.toLowerCase()
        }

}