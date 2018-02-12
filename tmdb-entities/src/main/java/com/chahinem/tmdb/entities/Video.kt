package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Video(
    val id: String? = null,
    @Json(name = "iso_639_1") val iso6391: String? = null,
    @Json(name = "iso_3166_1") val iso31661: String? = null,
    val key: String? = null,
    val name: String? = null,
    val site: String? = null,
    val size: Int? = null,
    val type: VideoType? = null
)
