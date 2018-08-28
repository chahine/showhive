package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Ratings(
    val rating: Double? = null,
    val votes: Int? = null,
    val distribution: Map<String, Int>? = null
)
