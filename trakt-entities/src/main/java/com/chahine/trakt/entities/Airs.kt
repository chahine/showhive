package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Airs(
    val day: String?,
    val time: String?,
    val timezone: String?
)
