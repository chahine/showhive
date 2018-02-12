package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Change(
    val id: String? = null,
    val action: String? = null,
    val time: String? = null,
    @Json(name = "iso_639_1") val iso6391: String? = null
)
