package com.chahine.tmdb.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CastMember(
    val id: Int? = null,
    @Json(name = "credit_id") val creditId: String? = null,
    val name: String? = null,
    @Json(name = "profile_path") val profilePath: String? = null,
    val character: String? = null,
    val order: Int? = null,
    @Json(name = "cast_id") val castId: Int? = null
)
