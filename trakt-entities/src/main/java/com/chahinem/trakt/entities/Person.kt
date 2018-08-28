package com.chahinem.trakt.entities

import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
class Person(
    val name: String? = null,
    val ids: PersonIds? = null,

    // extended info
    val biography: String? = null,
    val birthday: LocalDate? = null,
    val death: LocalDate? = null,
    val birthplace: String? = null,
    val homepage: String? = null
)
