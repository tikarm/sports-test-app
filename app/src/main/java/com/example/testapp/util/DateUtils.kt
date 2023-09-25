package com.example.testapp.util

import com.example.testapp.domain.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun parseDateFromFormat(inputDateStr: String, inputFormatStr: String): Long {
        return SimpleDateFormat(inputFormatStr, Locale.getDefault()).parse(inputDateStr)?.time ?: 0L
    }

    fun formatDate(inputDateStr: String, inputFormatStr: String): String {
        try {
            val inputFormat = SimpleDateFormat(inputFormatStr, Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(inputDateStr)

            val today = Calendar.getInstance()
            val yesterday = Calendar.getInstance()
            yesterday.add(Calendar.DAY_OF_YEAR, -1)

            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

            return if (isSameDay(date, today.time)) {
                "Today ${dateFormat.format(date)}"
            } else if (isSameDay(date, yesterday.time)) {
                "Yesterday ${dateFormat.format(date)}"
            } else {
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                outputFormat.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return inputDateStr
        }
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
    }


    fun isTomorrowDate(inputDateStr: String, inputFormatStr: String): Boolean {
        val currentDate = Date()

        val tomorrowDate = Calendar.getInstance().apply {
            time = currentDate
            add(Calendar.DAY_OF_YEAR, 1)
        }.time

        return try {
            val eventDate = SimpleDateFormat(inputFormatStr, Locale.getDefault()).parse(inputDateStr)
            eventDate != null && eventDate.after(currentDate) && eventDate.before(tomorrowDate)
        } catch (e: Exception) {
            false
        }
    }
}