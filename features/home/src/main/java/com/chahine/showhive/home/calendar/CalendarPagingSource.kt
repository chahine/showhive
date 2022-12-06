package com.chahine.showhive.home.calendar

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.api.entities.CalendarShowEntry
import com.chahine.trakt.api.entities.Extended
import okio.IOException
import retrofit2.HttpException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarPagingSource @Inject constructor(
    private val traktApi: TraktApi,
) : PagingSource<ZonedDateTime, CalendarUiModel>() {

    companion object {
        private const val DATE_RANGE = 30L
        private const val MAX_DATE_RANGE = 90L

        private val DATE_FORMATTER = ofPattern("yyyy-MM-dd")
    }

    override suspend fun load(params: LoadParams<ZonedDateTime>): LoadResult<ZonedDateTime, CalendarUiModel> {
        val page = params.key ?: ZonedDateTime.now()
        val startDate = page.format(DATE_FORMATTER)

        return try {
            val now = ZonedDateTime.now()
            val response = traktApi.myShows(startDate, DATE_RANGE.toInt(), Extended.FULL)
            LoadResult.Page(
                data = response.insertHeaders(),
                prevKey = if (now.minusDays(MAX_DATE_RANGE).isBefore(page)) page.minusDays(DATE_RANGE) else null,
                nextKey = if (now.plusDays(MAX_DATE_RANGE).isAfter(page)) page.plusDays(DATE_RANGE) else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<ZonedDateTime, CalendarUiModel>): ZonedDateTime? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
            anchorPage.prevKey?.plusDays(DATE_RANGE) ?: anchorPage.nextKey?.minusDays(DATE_RANGE)
        }
    }

    private fun List<CalendarShowEntry>.insertHeaders(): List<CalendarUiModel> {
        return this.groupBy { it.firstAired.toLocalDate() }.toSortedMap().map { (key, entries) ->
            val items = mutableListOf<CalendarUiModel>()
            items += CalendarUiModel.Header(key)
            items += entries.map { CalendarUiModel.Episode(it, null) }
            items
        }.flatten()
    }
}
