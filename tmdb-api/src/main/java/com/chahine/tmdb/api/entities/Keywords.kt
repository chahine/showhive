package com.chahine.tmdb.api.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Keywords(
    val id: Int? = null,

    // alternate = arrayOf("results")
    @Json(name = "keywords") val keywords: List<BaseKeyword>? = null
)
