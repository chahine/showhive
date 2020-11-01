package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Entries(
    val key: String? = null,
    val items: List<Change>? = null
)
