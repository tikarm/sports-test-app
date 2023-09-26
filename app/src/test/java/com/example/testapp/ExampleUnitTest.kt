package com.example.testapp

import com.example.testapp.domain.DATE_FORMAT
import com.example.testapp.util.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar

class FormatDateUnitTest {
    @Test
    fun testFormatDateToday() {
        val todayDate = SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time)
        val result = DateUtils.formatDate(todayDate, DATE_FORMAT)

        assertTrue(result.startsWith("Today "))
    }

    @Test
    fun testFormatDateYesterday() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)

        val inputDateStr = SimpleDateFormat(DATE_FORMAT).format(cal.time)


        val result = DateUtils.formatDate(inputDateStr, DATE_FORMAT)

        assertTrue(result.startsWith("Yesterday "))
    }

    @Test
    fun testFormatDateOtherDay() {
        val inputDateStr = "2023-09-24T01:51:44.627Z"
        val expected = "2023.09.24"

        val result = DateUtils.formatDate(inputDateStr, DATE_FORMAT)

        assertEquals(expected, result)
    }
}
