package com.chahine.showhive.home.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chahine.showhive.home.discover.DiscoverEvent.LoadTrendingShows
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import timber.log.Timber
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor
) : ViewModel() {

    internal val data: MutableLiveData<DiscoverModel> = MutableLiveData()
    internal val uiEvents: Subject<DiscoverEvent> = PublishSubject.create()

    init {
        Timber.d("DiscoverViewModel#${hashCode()} ")
        uiEvents
            .doOnNext { Timber.d("--> event: ${it.javaClass.simpleName} -- $it") }
            .publish { shared ->
                Observable.merge(
                    listOf(
                        shared
                            .ofType(LoadTrendingShows::class.java)
                            .compose(interactor.trendingShows())
                    )
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(data::postValue, Timber::e)
    }
}
