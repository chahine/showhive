package com.chahine.trakt.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Season(
    val number: Int?,
    val ids: SeasonIds?,
    val overview: String?,
    val rating: Double?,
    val votes: Int?,
    @Json(name = "episode_count") val episodeCount: Int?,
    @Json(name = "aired_episodes") val airedEpisodes: Int?,
    val episodes: List<Episode>?
)
