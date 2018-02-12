package com.chahinem.trakt.entities

import org.threeten.bp.OffsetDateTime

class Episode(
    val title: String? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    val updated_at: OffsetDateTime? = null,
    val available_translations: List<String>? = null,
    val season: Int? = null,
    val number: Int? = null,
    val ids: EpisodeIds? = null,
    val number_abs: Int? = null,
    val first_aired: OffsetDateTime? = null
)
