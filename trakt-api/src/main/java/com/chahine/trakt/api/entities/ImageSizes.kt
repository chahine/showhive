package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageSizes(
    val full: String?
)
