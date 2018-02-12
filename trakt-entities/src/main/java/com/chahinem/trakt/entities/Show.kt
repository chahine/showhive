package com.chahinem.trakt.entities

import org.threeten.bp.OffsetDateTime

class Show(
    val title: String? = null,
    val overview: String? = null,
    val rating: Double? = null,
    val votes: Int? = null,
    val updated_at: OffsetDateTime? = null,
    val available_translations: List<String>? = null,
    val year: Int? = null,
    val ids: ShowIds? = null,
    val first_aired: OffsetDateTime? = null,
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
