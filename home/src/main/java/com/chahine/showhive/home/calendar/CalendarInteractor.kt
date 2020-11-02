package com.chahine.showhive.home.calendar

import com.chahine.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahine.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahine.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahine.trakt.api.TraktApi
import io.reactivex.rxjava3.core.ObservableTransformer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarInteractor @Inject constructor(private val traktApi: TraktApi) {

    companion object {
        // The maximum amount of days you can send is 31
        private const val DAY_WINDOW = 15
        private val DAY_FORMATTER = ofPattern("yyyy-MM-dd")
    }

    fun calendar(): ObservableTransformer<in CalendarEvent, out CalendarModel> {
        return ObservableTransformer { event ->
            event.switchMap {
                val startDate = ZonedDateTime.now().minusDays(DAY_WINDOW.toLong()).format(DAY_FORMATTER)
                traktApi
                    .myShows(startDate, DAY_WINDOW * 2)
                    .map { episodes ->
                        if (episodes.isEmpty()) {
                            return@map CalendarEmptyItemView.Item
                        }
                        val items = mutableListOf<CalendarAdapter.Item>()
                        episodes
                            .groupBy { it.firstAired.toLocalDate() }
                            .forEach { (date, episode) ->
                                items += DateHeaderItemView.Item(date)
                                items += episode.map { EpisodeItemView.Item(it) }
                            }
                        CalendarCardSuccess(items)
                    }
                    .toObservable()
                    .cast(CalendarModel::class.java)
                    .onErrorReturn { CalendarFailure(it) }
                    .startWithItem(CalendarProgress)
            }
        }
    }
}
