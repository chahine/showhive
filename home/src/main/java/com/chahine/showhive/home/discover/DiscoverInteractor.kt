package com.chahine.showhive.home.discover

import android.content.res.Resources
import com.chahine.showhive.home.discover.DiscoverEvent.LoadTrendingShows
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverFailure
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverIdle
import com.chahine.showhive.home.discover.DiscoverModel.DiscoverSuccess
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.entities.Extended
import com.chahine.trakt.entities.TrendingShow
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    private val resources: Resources,
    private val traktApi: TraktApi,
) {

    companion object {
        private const val SEPARATOR = " • "
    }

    fun trendingShows(): ObservableTransformer<in LoadTrendingShows, out DiscoverModel> {
        return ObservableTransformer { event ->
            event.switchMap { loadTrendingShows ->
                traktApi
                    .trending(loadTrendingShows.page, 20, Extended.FULL)
                    .map { shows -> DiscoverSuccess(shows.map { makeShowItem(it) }) }
                    .toObservable()
                    .cast(DiscoverModel::class.java)
                    .onErrorReturn { DiscoverFailure(it) }
                    .startWithItem(DiscoverIdle)
            }
        }
    }

    private fun makeShowItem(item: TrendingShow): DiscoverAdapter.Item {
        val show = item.show

        val line1 = listOfNotNull(show.title).joinToString(SEPARATOR)
        val line2 = listOfNotNull(show.overview).joinToString(SEPARATOR)
        val line3 = listOfNotNull(
            show.network,
            show.certification,
            show.airs.time
        ).joinToString(SEPARATOR)

        return ShowItemView.Item(line1, line2, line3)
    }
}
