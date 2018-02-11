package com.chahinem.trakt.entities

import org.threeten.bp.OffsetDateTime

class Show(
    val year: Int,
    val ids: ShowIds,
    val firstAired: OffsetDateTime,
    val airs: Airs,
    val runtime: Int,
    val certification: String,
    val network: String,
    val country: String,
    val trailer: String,
    val homepage: String,
    val status: Status,
    val language: String,
    val genres: List<String>
)
