package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Ratings(
    val rating: Double?,
    val votes: Int?,
    val distribution: Map<String, Int>?
)
