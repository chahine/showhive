package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Changes(
    val changes: List<Entries>
)
