package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Season(
    val number: Int? = null,
    val ids: SeasonIds? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    @Json(name = "episode_count") val episodeCount: Int? = null,
    @Json(name = "aired_episodes") val airedEpisodes: Int? = null,
    val episodes: List<Episode>? = null
)
