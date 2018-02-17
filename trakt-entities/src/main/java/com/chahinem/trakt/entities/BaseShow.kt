package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

class BaseShow(
    val show: Show? = null,
    val seasons: List<BaseSeason>? = null,
    @Json(name = "last_collected_at") val lastCollectedAt: ZonedDateTime? = null,
    @Json(name = "listed_at") val listedAt: ZonedDateTime? = null,
    val plays: Int? = null,
    @Json(name = "last_watched_at") val lastWatchedAt: ZonedDateTime? = null,
    val aired: Int? = null,
    val completed: Int? = null,
    @Json(name = "hidden_seasons") val hiddenSeasons: List<Season>? = null,
    @Json(name = "next_episode") val nextEpisode: Episode? = null
)
