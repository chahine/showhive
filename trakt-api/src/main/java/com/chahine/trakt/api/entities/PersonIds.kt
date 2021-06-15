package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PersonIds(
    val trakt: Int?,
    val imdb: Int?,
    val tmdb: Int?,
    val slug: String?,
    val tvrage: String?
)
