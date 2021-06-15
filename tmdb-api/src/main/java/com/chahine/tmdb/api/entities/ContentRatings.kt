package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ContentRatings(
    val id: Int? = null,
    val results: List<ContentRating>? = null
)
