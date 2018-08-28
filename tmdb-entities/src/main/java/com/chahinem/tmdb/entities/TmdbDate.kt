package com.chahinem.tmdb.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TmdbDate {

//  private val date: Date? = null

//  constructor(date: String) {
//    var parsedDate: Date?
//    try {
//      parsedDate = TMDB_DATE_FORMAT.get().parse(date)
//    } catch (e: ParseException) {
//      parsedDate = null
//    }
//
//    this.date = parsedDate
//  }

//  override fun toString(): String {
//    return TMDB_DATE_FORMAT.get().format(date)
//  }
//
//  companion object {
//
//    private val TMDB_DATE_FORMAT = object : ThreadLocal<DateFormat>() {
//      public override fun initialValue(): DateFormat {
//        return SimpleDateFormat("yyyy-MM-dd")
//      }
//    }
//  }
}