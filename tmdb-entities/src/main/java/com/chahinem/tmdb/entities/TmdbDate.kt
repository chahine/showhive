package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
class TmdbDate(val dateString: String) {

    companion object {
        private val TMDB_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    val date: Date?

    init {
        this.date = try {
            TMDB_DATE_FORMAT.parse(dateString)
        } catch (e: ParseException) {
            null
        }
    }

    override fun toString(): String {
        return if (date != null) {
            TMDB_DATE_FORMAT.format(date)
        } else {
            ""
        }
    }
}
