package com.chahine.showhive.home.discover

import android.content.res.Resources
import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.calendar.CalendarModel
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    private val resources: Resources,
    private val traktApi: TraktApi,
) {

    fun trendingShows(): ObservableTransformer<in DiscoverEvent, out DiscoverModel> {
        return ObservableTransformer { event ->
            event.switchMap {
                traktApi
                    .trending(1, 20, Extended.FULL)
                    .map { shows -> CalendarModel.CalendarCardSuccess(shows.map { makeShowItem(it) }) }
                    .toObservable()
                    .cast(DiscoverModel::class.java)
                    .onErrorReturn { DiscoverModel.DiscoverFailure(it) }
                    .startWithItem(DiscoverModel.DiscoverIdle)
            }
        }
    }

    private fun makeShowItem(show: TrendingShow): CalendarAdapter.Item {
        TODO("Not yet implemented")
    }
}
