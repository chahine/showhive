package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ratings(
    val rating: Double?,
    val votes: Int?,
    val distribution: Map<String, Int>?
)
