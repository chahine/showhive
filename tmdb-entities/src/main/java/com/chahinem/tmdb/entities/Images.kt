package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Images(
    val id: Int? = null,
    val backdrops: List<Image>? = null,
    val posters: List<Image>? = null,
    val stills: List<Image>? = null
)
