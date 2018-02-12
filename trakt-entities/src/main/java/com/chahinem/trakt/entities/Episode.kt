package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import org.threeten.bp.OffsetDateTime

class Episode(
    val title: String? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    @Json(name = "updated_at") val updatedAt: OffsetDateTime? = null,
    @Json(name = "available_translations") val availableTranslations: List<String>? = null,
    val season: Int? = null,
    val number: Int? = null,
    val ids: EpisodeIds? = null,
    @Json(name = "number_abs") val numberAbs: Int? = null,
    @Json(name = "first_aired") val firstAired: OffsetDateTime? = null
)
