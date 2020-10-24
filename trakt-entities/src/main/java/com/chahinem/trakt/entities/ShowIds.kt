package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ShowIds(
    val trakt: Int,
    val imdb: String,
    val tmdb: Int,
    val slug: String?,
    val tvdb: Int?,
    val tvrage: Int?
)
