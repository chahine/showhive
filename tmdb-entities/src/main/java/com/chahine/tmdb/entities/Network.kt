package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Network(
    val id: Int? = null,
    val name: String? = null
)
