package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Translation(
    val language: String?,
    val title: String?,
    val overview: String?
)
