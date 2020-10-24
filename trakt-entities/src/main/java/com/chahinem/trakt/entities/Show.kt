package com.chahinem.trakt.entities

import com.squareup.moshi.Json
import java.time.ZonedDateTime

class Show(
    val title: String? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    @Json(name = "updated_at") val updatedAt: ZonedDateTime? = null,
    @Json(name = "available_translations") val availableTranslations: List<String>? = null,
    val year: Int? = null,
    val ids: ShowIds? = null,
    @Json(name = "first_aired") val firstAired: ZonedDateTime? = null,
    val airs: Airs? = null,
    val runtime: Int? = null,
    val certification: String? = null,
    val network: String? = null,
    val country: String? = null,
    val trailer: String? = null,
    val homepage: String? = null,
    val status: Status? = null,
    val language: String? = null,
    val genres: List<String>? = null
)
