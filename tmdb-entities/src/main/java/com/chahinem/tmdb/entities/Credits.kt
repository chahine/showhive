package com.chahinem.tmdb.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Credits(
    val id: Int? = null,
    val cast: List<CastMember>? = null,
    val crew: List<CrewMember>? = null,
    @Json(name = "guest_stars") val guestStars: List<CastMember>? = null
)
