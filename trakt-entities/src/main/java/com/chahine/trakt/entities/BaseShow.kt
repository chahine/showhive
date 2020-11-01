package com.chahine.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
class BaseShow(
    val show: Show?,
    val seasons: List<BaseSeason>?,
    @Json(name = "last_collected_at") val lastCollectedAt: ZonedDateTime?,
    @Json(name = "listed_at") val listedAt: ZonedDateTime?,
    val plays: Int?,
    @Json(name = "last_watched_at") val lastWatchedAt: ZonedDateTime?,
    val aired: Int?,
    val completed: Int?,
    @Json(name = "hidden_seasons") val hiddenSeasons: List<Season>?,
    @Json(name = "next_episode") val nextEpisode: Episode?
)
