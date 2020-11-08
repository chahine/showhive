package com.chahine.trakt.entities

import com.squareup.moshi.Json

enum class Status {

    @Json(name = "ended")
    ENDED,

    @Json(name = "returning series")
    RETURNING,

    @Json(name = "canceled")
    CANCELED,

    @Json(name = "in production")
    IN_PRODUCTION;
}
