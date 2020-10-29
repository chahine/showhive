package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
class CalendarShowEntry(
    @Json(name = "first_aired") val firstAired: ZonedDateTime,
    val episode: Episode,
    val show: Show
)
