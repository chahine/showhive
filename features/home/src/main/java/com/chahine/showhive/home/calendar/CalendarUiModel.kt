package com.chahine.showhive.home.calendar

import com.chahine.trakt.api.entities.CalendarShowEntry
import java.time.LocalDate

sealed class CalendarUiModel {
    data class Episode(val entry: CalendarShowEntry, val posterUrl: String) : CalendarUiModel()
    data class Header(val date: LocalDate) : CalendarUiModel()
}
