package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
class CalendarShowEntry(
    @Json(name = "first_aired") val firstAired: ZonedDateTime? = null,
    val episode: Episode? = null,
    val show: Show? = null
)
