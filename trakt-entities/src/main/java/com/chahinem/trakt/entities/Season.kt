package com.chahinem.trakt.entities

class Season(
    val number: Int? = null,
    val ids: SeasonIds? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    val episode_count: Int? = null,
    val aired_episodes: Int? = null,
    val episodes: List<Episode>? = null
)
