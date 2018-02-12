package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Translation(
    @Json(name = "iso_639_1") val iso6391: String? = null,
    val name: String? = null,
    @Json(name = "english_name") val englishName: String? = null
)
