package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TrendingShow(
    val watchers: Int?,
    val show: Show
)
