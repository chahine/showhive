package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CastMember(
    val character: String?,
    val movie: Movie?,
    val show: Show?,
    val person: Person?
)
