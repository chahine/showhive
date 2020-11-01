package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Videos(
    val id: Int,
    val results: List<Video>
)
