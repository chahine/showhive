package com.chahinem.trakt.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter.ofPattern

class ZonedDateTimeConverter {
    @ToJson
    fun toJson(zonedDateTime: ZonedDateTime): String {
        return FORMATTER.format(zonedDateTime)
    }

    @FromJson
    fun fromJson(json: String): ZonedDateTime {
        return FORMATTER.parse(json, LocalDateTime.FROM)
            .atZone(ZoneId.of("GMT+0"))
            .withZoneSameInstant(ZonedDateTime.now().zone)
    }

    companion object {
        private val FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }
}
