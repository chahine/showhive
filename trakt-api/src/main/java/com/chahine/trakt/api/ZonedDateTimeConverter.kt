package com.chahine.trakt.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.ZonedDateTime

class ZonedDateTimeConverter {

    @ToJson
    fun toJson(zonedDateTime: ZonedDateTime): String {
        return zonedDateTime.toString()
    }

    @FromJson
    fun fromJson(json: String): ZonedDateTime {
        return ZonedDateTime.parse(json)
    }
}
