package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import org.threeten.bp.OffsetDateTime

class Show(
    val title: String,
    val overview: String?,
    val rating: Double?,
    val votes: Int?,
    @Json(name = "updated_at") val updatedAt: OffsetDateTime?,
    @Json(name = "available_translations") val availableTranslations: List<String>?,
    val year: Int?,
    val ids: ShowIds,
    @Json(name = "first_aired") val firstAired: OffsetDateTime?,
    val airs: Airs?,
    val runtime: Int?,
    val certification: String?,
    val network: String?,
    val country: String?,
    val trailer: String?,
    val homepage: String?,
    val status: Status?,
    val language: String?,
    val genres: List<String>?
)
