package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonIds(
    val tvdb: Int?,
    val tmdb: Int?,
    val trakt: Int?,
    val tvrage: Int?
)
