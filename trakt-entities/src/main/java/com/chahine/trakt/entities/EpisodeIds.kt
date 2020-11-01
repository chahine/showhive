package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EpisodeIds(
    val trakt: Int?,
    val imdb: String?,
    val tmdb: Int?,
    val tvdb: Int?,
    val tvrage: Int?
)
