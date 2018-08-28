package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseSeason(
    val number: Int? = null,
    val episodes: List<BaseEpisode>? = null,
    val aired: Int? = null,
    val completed: Int? = null
)
