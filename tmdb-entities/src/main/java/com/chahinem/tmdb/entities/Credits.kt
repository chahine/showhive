package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Credits(
    val id: Int? = null,
    val cast: List<CastMember>? = null,
    val crew: List<CrewMember>? = null,
    @Json(name = "guest_stars") val guestStars: List<CastMember>? = null
)
