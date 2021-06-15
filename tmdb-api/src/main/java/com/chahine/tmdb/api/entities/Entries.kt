package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Entries(
    val key: String? = null,
    val items: List<Change>? = null
)
