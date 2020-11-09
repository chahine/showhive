package com.chahine.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.DayOfWeek
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.format.TextStyle
import java.util.Locale

class DayOfWeekAdapter {

    @ToJson
    fun toJson(dayOfWeek: DayOfWeek): String {
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)
    }

    @FromJson
    fun fromJson(input: String): DayOfWeek? {
        return when (input) {
            "Monday" -> MONDAY
            "Tuesday" -> TUESDAY
            "Wednesday" -> WEDNESDAY
            "Thursday" -> THURSDAY
            "Friday" -> FRIDAY
            "Saturday" -> SATURDAY
            "Sunday" -> SUNDAY
            else -> null
        }
    }
}
