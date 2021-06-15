package com.chahine.tmdb.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Translations(
    val id: Int? = null,
    val translations: List<Translation>? = null
)
