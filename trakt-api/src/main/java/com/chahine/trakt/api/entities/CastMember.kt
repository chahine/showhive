package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastMember(
    val character: String?,
    val movie: Movie?,
    val show: Show?,
    val person: Person?
)
