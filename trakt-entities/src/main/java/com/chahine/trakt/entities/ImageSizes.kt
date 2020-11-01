package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ImageSizes(
    val full: String?
)
