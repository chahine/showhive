package com.chahinem.showhive.home.calendar

import com.chahinem.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahinem.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahinem.trakt.api.TraktApi
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class CalendarInteractor @Inject constructor(private val traktApi: TraktApi) {

  fun calendar(): ObservableTransformer<in CalendarEvent, out CalendarModel> {
    return ObservableTransformer {
      it.switchMap {
        Observable
            .just(CalendarCardSuccess(emptyList()))
            .cast(CalendarModel::class.java)
            .onErrorReturn { CalendarFailure(it) }
            .startWith(CalendarProgress())
      }
    }
  }
}
