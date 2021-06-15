package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
open class BaseEpisode(
    val number: Int?,
    @Json(name = "collected_at") val collectedAt: ZonedDateTime?,
    val plays: Int?,
    @Json(name = "last_watched_at") val lastWatchedAt: ZonedDateTime?,
    val completed: Boolean?
)
