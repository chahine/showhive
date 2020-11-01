package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RatingObject(
    val value: Double? = null
)
