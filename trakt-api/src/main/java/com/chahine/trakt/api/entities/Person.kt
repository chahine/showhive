package com.chahine.trakt.api.entities

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
class Person(
    val name: String?,
    val ids: PersonIds?,

    // extended info
    val biography: String?,
    val birthday: LocalDate?,
    val death: LocalDate?,
    val birthplace: String?,
    val homepage: String?
)
