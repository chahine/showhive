package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
class Movie(
    val year: Int?,
    val ids: MovieIds?,
    val certification: String?,
    val tagline: String?,
    val released: LocalDate?,
    val runtime: Int?,
    val trailer: String?,
    val homepage: String?,
    val language: String?,
    val genres: List<String>?
)
