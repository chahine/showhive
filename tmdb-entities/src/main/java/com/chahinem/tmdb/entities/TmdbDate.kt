package com.chahinem.tmdb.entities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TmdbDate(date: String) {

    companion object {
        private val TMDB_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    }

    private val date: Date?

    init {
        this.date = try {
            TMDB_DATE_FORMAT.parse(date)
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
