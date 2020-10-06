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
        var parsedDate: Date?
        try {
            parsedDate = TMDB_DATE_FORMAT.parse(date)
        } catch (e: ParseException) {
            parsedDate = null
        }
        this.date = parsedDate
    }

    override fun toString(): String {
        return TMDB_DATE_FORMAT.format(date)
    }
}
