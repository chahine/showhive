package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PersonIds(
    val trakt: Int? = null,
    val imdb: Int? = null,
    val tmdb: Int? = null,
    val slug: String? = null,
    val tvrage: String? = null
)
