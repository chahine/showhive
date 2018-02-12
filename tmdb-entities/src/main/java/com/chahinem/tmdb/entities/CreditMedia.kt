package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class CreditMedia(
    val id: Int? = null,
    val name: String? = null,
    @Json(name = "original_name") val originalName: String? = null,
    val character: String? = null,
    val seasons: List<TvSeason>? = null
)
