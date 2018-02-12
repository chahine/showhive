package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import org.threeten.bp.OffsetDateTime

open class BaseEpisode(
    val number: Int? = null,
    @Json(name = "collected_at") val collectedAt: OffsetDateTime? = null,
    val plays: Int? = null,
    @Json(name = "last_watched_at") val lastWatchedAt: OffsetDateTime? = null,
    val completed: Boolean? = null
)
