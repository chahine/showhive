package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class BaseCompany(
    val id: Int? = null,
    val name: String? = null,
    @Json(name = "logo_path") val logoPath: String? = null
)
