package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Images(
    val avatar: ImageSizes? = null
)
