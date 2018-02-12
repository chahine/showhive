package com.chahinem.tmdb.entities

import com.squareup.moshi.Json

class Status(
    @Json(name = "status_code") val statusCode: Int? = null,
    @Json(name = "status_message") val statusMessage: String? = null
)
