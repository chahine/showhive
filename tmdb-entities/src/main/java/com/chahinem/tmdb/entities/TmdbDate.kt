package com.chahinem.tmdb.entities

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class TmdbDate {

  private val date: Date?

  constructor(date: Date) {
    this.date = date
  }

  constructor(date: String) {
    var parsedDate: Date?
    try {
      parsedDate = TMDB_DATE_FORMAT.get().parse(date)
    } catch (e: ParseException) {
      parsedDate = null
    }

    this.date = parsedDate
  }

  override fun toString(): String {
    return TMDB_DATE_FORMAT.get().format(date)
  }

  companion object {

    private val TMDB_DATE_FORMAT = object : ThreadLocal<DateFormat>() {
      public override fun initialValue(): DateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
      }
    }
  }
}