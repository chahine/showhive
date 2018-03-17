package com.chahinem.showhive.home.calendar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chahinem.showhive.home.calendar.CalendarEvent.LoadCalendar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import timber.log.Timber
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val interactor: CalendarInteractor
) : ViewModel() {

  internal val data: MutableLiveData<CalendarModel> = MutableLiveData()
  internal val uiEvents: Subject<CalendarEvent> = PublishSubject.create()

  init {
    Timber.d("CalendarViewModel#${hashCode()} ")
    uiEvents
        .doOnNext { Timber.d("--> event: ${it.javaClass.simpleName} -- $it") }
        .publish { shared ->
          Observable.merge(listOf(
              shared
                  .ofType(LoadCalendar::class.java)
                  .compose(interactor.calendar())
          ))
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(data::postValue, Timber::e)
  }
}
