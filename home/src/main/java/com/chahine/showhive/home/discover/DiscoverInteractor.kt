package com.chahine.showhive.home.discover

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import com.chahine.showhive.home.discover.DiscoverEvent.RefreshTrendingShows
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverFailure
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverIdle
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverSuccess
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.ObservableTransformer
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    private val pagingSource: DiscoverPagingSource,
) {

    companion object {
        private const val PAGE_LIMIT = 20
        private const val SEPARATOR = " • "
    }

    fun refresh(): ObservableTransformer<in RefreshTrendingShows, out DiscoverModel> {
        return ObservableTransformer { event ->
            event.switchMap {

                val loadParam = PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = PAGE_LIMIT,
                    placeholdersEnabled = false
                )
                pagingSource
                    .loadSingle(loadParam)
                    .map {
                        when (it) {
                            is LoadResult.Error -> DiscoverFailure(it.throwable)
                            is LoadResult.Page -> DiscoverSuccess(it.data.map(::makeShowItem))
                        }
                    }
                    .toObservable()
                    .startWithItem(DiscoverIdle)
            }
        }
    }

    private fun makeShowItem(item: TrendingShow): DiscoverAdapter.Item {
        val show = item.show
        val airs = show.airs

        val time = if (!airs.time.isNullOrBlank() && !airs.timezone.isNullOrBlank()) {
            val dateTime = LocalTime
                .parse(airs.time, DateTimeFormatter.ofPattern("HH:mm"))
                .atDate(LocalDate.now())
            ZonedDateTime
                .ofLocal(dateTime, ZoneId.of(airs.timezone), null)
                .format(DateTimeFormatter.ofPattern("hh:mm a"))
        } else {
            null
        }

        val line1 = listOfNotNull(show.title).joinToString(SEPARATOR)
        val line2 = listOfNotNull(show.overview).joinToString(SEPARATOR)
        val line3 = listOfNotNull(show.network, show.certification, time).joinToString(SEPARATOR)

        return ShowItemView.Item(line1, line2, line3)
    }
}
