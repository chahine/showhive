package com.chahine.tmdb.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ContentRating(
    @Json(name = "iso_639_1") val iso6391: String? = null,
    val rating: String? = null
)
