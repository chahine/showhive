package com.chahine.trakt.api

import com.chahine.api.DayOfWeekAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoshiFactory @Inject constructor() {

    fun make(): Moshi {
        return Moshi.Builder()
            .add(ZonedDateTimeConverter())
            .add(DayOfWeekAdapter())
            .build()
    }
}
