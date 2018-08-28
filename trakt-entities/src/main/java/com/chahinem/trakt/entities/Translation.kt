package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Translation(
    val language: String? = null,
    val title: String? = null,
    val overview: String? = null
)
