package com.chahine.showhive.home.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chahine.showhive.home.calendar.CalendarEvent.LoadCalendar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
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
                Observable.merge(
                    listOf(
                        shared
                            .ofType(LoadCalendar::class.java)
                            .compose(interactor.calendar())
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(data::postValue, Timber::e)
    }
}
