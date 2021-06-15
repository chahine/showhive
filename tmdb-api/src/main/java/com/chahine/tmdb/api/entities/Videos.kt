package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Videos(
    val id: Int,
    val results: List<Video>
)
