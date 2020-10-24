package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
class CalendarMovieEntry(
    val released: LocalDate?,
    val movie: Movie?
)
