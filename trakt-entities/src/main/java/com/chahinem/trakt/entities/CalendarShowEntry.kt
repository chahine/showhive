package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import java.time.ZonedDateTime

class CalendarShowEntry(
    @Json(name = "first_aired") val firstAired: ZonedDateTime? = null,
    val episode: Episode? = null,
    val show: Show? = null
)
