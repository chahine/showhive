package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<CastMember>?,
    val crew: Crew?
)
