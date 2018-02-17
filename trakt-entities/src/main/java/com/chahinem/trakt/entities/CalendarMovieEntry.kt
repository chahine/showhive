package com.chahinem.trakt.entities

import org.threeten.bp.LocalDate

class CalendarMovieEntry(
    val released: LocalDate? = null,
    val movie: Movie? = null
)
