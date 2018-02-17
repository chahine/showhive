package com.chahinem.showhive.home.calendar

import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahinem.trakt.api.TraktApi
import io.reactivex.ObservableTransformer
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarInteractor @Inject constructor(private val traktApi: TraktApi) {

  fun calendar(): ObservableTransformer<in CalendarEvent, out CalendarModel> {
    return ObservableTransformer {
      it.switchMap {
        val startDate = ZonedDateTime.now().minusDays(15).format(dayFormatter)
        traktApi.myShows(startDate, 60)
            .concatMapIterable { it }
            .map { EpisodeItemView.Item(it) }
            .toList()
            .toObservable()
            .map { CalendarCardSuccess(it) as CalendarModel }
            .onErrorReturn { CalendarFailure(it) }
            .startWith(CalendarProgress())
      }
    }
  }

  companion object {
    val dayFormatter: DateTimeFormatter? = ofPattern("yyyy-MM-dd")
  }
}
