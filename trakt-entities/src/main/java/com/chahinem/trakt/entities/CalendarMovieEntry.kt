package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
class CalendarMovieEntry(
    val released: LocalDate? = null,
    val movie: Movie? = null
)
