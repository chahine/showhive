package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Images(
    val id: Int,
    val backdrops: List<Image>,
    val posters: List<Image>,
    val stills: List<Image>? = null
)
