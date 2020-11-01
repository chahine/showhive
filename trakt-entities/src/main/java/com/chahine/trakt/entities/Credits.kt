package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Credits(
    val cast: List<CastMember>?,
    val crew: Crew?
)
