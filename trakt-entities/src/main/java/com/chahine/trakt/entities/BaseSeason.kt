package com.chahine.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseSeason(
    val number: Int?,
    val episodes: List<BaseEpisode>?,
    val aired: Int?,
    val completed: Int?
)
