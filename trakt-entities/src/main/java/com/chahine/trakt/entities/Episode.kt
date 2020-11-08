package com.chahine.trakt.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
class Episode(
    val title: String,
    val overview: String?,
    val rating: Double?,
    val votes: Int?,
    @Json(name = "updated_at") val updatedAt: ZonedDateTime?,
    @Json(name = "available_translations") val availableTranslations: List<String>?,
    val season: Int?,
    val number: Int?,
    val ids: EpisodeIds?,
    @Json(name = "number_abs") val numberAbs: Int?,
    @Json(name = "first_aired") val firstAired: ZonedDateTime?
)
