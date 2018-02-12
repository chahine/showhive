package com.chahinem.trakt.entities

import org.threeten.bp.OffsetDateTime

class BaseShow(
    val show: Show? = null,
    val seasons: List<BaseSeason>? = null,
    val last_collected_at: OffsetDateTime? = null,
    val listed_at: OffsetDateTime? = null,
    val plays: Int? = null,
    val last_watched_at: OffsetDateTime? = null,
    val aired: Int? = null,
    val completed: Int? = null,
    val hidden_seasons: List<Season>? = null,
    val next_episode: Episode? = null
)
