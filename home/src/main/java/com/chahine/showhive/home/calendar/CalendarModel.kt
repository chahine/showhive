package com.chahine.showhive.home.calendar

import com.chahine.trakt.entities.CalendarShowEntry
import java.time.LocalDate

sealed class CalendarModel {
    data class Episode(val entry: CalendarShowEntry) : CalendarModel()
    data class Header(val date: LocalDate) : CalendarModel()
}
