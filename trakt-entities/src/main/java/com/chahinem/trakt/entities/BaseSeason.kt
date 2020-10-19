package com.chahinem.trakt.entities

open class BaseSeason(
    val number: Int? = null,
    val episodes: List<BaseEpisode>? = null,
    val aired: Int? = null,
    val completed: Int? = null
)
