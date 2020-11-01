package com.chahine.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Changes(
    val changes: List<Entries>
)
