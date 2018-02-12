package com.chahinem.tmdb.entities

class Credits(
    val id: Int? = null,
    val cast: List<CastMember>? = null,
    val crew: List<CrewMember>? = null,
    val guest_stars: List<CastMember>? = null
)
