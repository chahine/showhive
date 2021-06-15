package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Genre(
    val id: Int? = null,
    val name: String? = null
)
