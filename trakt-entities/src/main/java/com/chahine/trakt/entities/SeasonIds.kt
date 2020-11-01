package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SeasonIds(
    val tvdb: Int?,
    val tmdb: Int?,
    val trakt: Int?,
    val tvrage: Int?
)
