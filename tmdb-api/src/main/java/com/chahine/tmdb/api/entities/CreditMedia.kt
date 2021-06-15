package com.chahine.tmdb.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CreditMedia(
    val id: Int? = null,
    val name: String? = null,
    @Json(name = "original_name") val originalName: String? = null,
    val character: String? = null,
    val seasons: List<TvSeason>? = null
)
