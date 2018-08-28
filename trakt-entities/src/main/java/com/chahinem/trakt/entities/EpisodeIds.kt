package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EpisodeIds(
    val trakt: Int? = null,
    val imdb: String? = null,
    val tmdb: Int? = null,
    val tvdb: Int? = null,
    val tvrage: Int? = null
)
