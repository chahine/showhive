package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class AlternativeTitle(
    @Json(name = "iso_3166_1") val iso31661: String? = null,
    val title: String? = null
)
