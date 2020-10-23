package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import java.time.ZonedDateTime

open class BaseEpisode(
    val number: Int? = null,
    @Json(name = "collected_at") val collectedAt: ZonedDateTime? = null,
    val plays: Int? = null,
    @Json(name = "last_watched_at") val lastWatchedAt: ZonedDateTime? = null,
    val completed: Boolean? = null
)
