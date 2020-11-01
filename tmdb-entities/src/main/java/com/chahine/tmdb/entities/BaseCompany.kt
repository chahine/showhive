package com.chahine.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseCompany(
    val id: Int? = null,
    val name: String? = null,
    @Json(name = "logo_path") val logoPath: String? = null
)
