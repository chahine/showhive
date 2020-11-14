package com.chahine.showhive.home.calendar

import androidx.paging.rxjava3.RxPagingSource
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.entities.CalendarShowEntry
import com.chahine.trakt.entities.Extended
import io.reactivex.rxjava3.core.Single
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarPagingSource @Inject constructor(
    private val traktApiClient: TraktApiClient,
) : RxPagingSource<ZonedDateTime, CalendarShowEntry>() {

    companion object {
        private const val DATE_RANGE = 30L
        private val DATE_FORMATTER = ofPattern("yyyy-MM-dd")
    }

    override fun loadSingle(params: LoadParams<ZonedDateTime>): Single<LoadResult<ZonedDateTime, CalendarShowEntry>> {
        val page = params.key ?: ZonedDateTime.now()
        val startDate = page.format(DATE_FORMATTER)

        return traktApiClient
            .myShows(startDate, 30, Extended.FULL)
            .map<LoadResult<ZonedDateTime, CalendarShowEntry>> {
                val now = ZonedDateTime.now()

                LoadResult.Page(
                    data = it,
                    prevKey = if (now.minusDays(90L).isBefore(page)) page.minusDays(DATE_RANGE) else null,
                    nextKey = if (now.plusDays(90L).isAfter(page)) page.plusDays(DATE_RANGE) else null
                )
            }
            .onErrorReturn { LoadResult.Error(it) }
    }
}
