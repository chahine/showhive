package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class ContentRating(
    @Json(name = "iso_639_1") val iso6391: String? = null,
    val rating: String? = null
)
