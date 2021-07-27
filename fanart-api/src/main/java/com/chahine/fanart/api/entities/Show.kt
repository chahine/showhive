package com.chahine.fanart.api.entities

import com.squareup.moshi.Json

data class Show(
    val name: String,
    @Json(name = "thetvdb_id")
    val id: String,
    @Json(name = "tvposter")
    val posters: List<Poster>,
)
