package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

@JsonClass(generateAdapter = true)
class Episode(
    val title: String? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    @Json(name = "updated_at") val updatedAt: ZonedDateTime? = null,
    @Json(name = "available_translations") val availableTranslations: List<String>? = null,
    val season: Int? = null,
    val number: Int? = null,
    val ids: EpisodeIds? = null,
    @Json(name = "number_abs") val numberAbs: Int? = null,
    @Json(name = "first_aired") val firstAired: ZonedDateTime? = null
)
