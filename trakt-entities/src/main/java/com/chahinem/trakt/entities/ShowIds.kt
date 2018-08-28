package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ShowIds(
    val trakt: Int,
    val imdb: String,
    val tmdb: Int,
    val slug: String? = null,
    val tvdb: Int? = null,
    val tvrage: Int? = null
)
