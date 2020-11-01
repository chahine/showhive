package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AlternativeTitle(
    @Json(name = "iso_3166_1") val iso31661: String? = null,
    val title: String? = null
)
