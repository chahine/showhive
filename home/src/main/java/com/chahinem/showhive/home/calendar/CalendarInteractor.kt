package com.chahinem.showhive.home.calendar

import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahinem.trakt.api.TraktApi
import io.reactivex.rxjava3.core.ObservableTransformer
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarInteractor @Inject constructor(private val traktApi: TraktApi) {

    fun calendar(): ObservableTransformer<in CalendarEvent, out CalendarModel> {
        return ObservableTransformer { event ->
            event.switchMap {
                val startDate = ZonedDateTime.now()
                    .minusDays(DAY_WINDOW.toLong()).format(DAY_FORMATTER)
                traktApi
                    .myShows(startDate, DAY_WINDOW * 2)
                    .concatMapIterable { it }
                    .groupBy { it.firstAired?.toLocalDate() }
                    .filter { it.key != null }
                    .concatMap { group ->
                        group
                            .map { EpisodeItemView.Item(it) }
                            .cast(CalendarAdapter.Item::class.java)
                            .startWithItem(DateHeaderItemView.Item(group.key!!))
                    }
                    .defaultIfEmpty(CalendarEmptyItemView.Item())
                    .toList()
                    .toObservable()
                    .map { CalendarCardSuccess(it) as CalendarModel }
                    .onErrorReturn { CalendarFailure(it) }
                    .startWithItem(CalendarProgress())
            }
        }
    }

    companion object {
        // The maximum amount of days you can send is 31
        private const val DAY_WINDOW = 15
        private val DAY_FORMATTER = ofPattern("yyyy-MM-dd")
    }
}
