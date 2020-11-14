package com.chahine.showhive.home.calendar

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.chahine.trakt.entities.CalendarShowEntry
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CalendarRepository @Inject constructor(
    private val pagingSource: CalendarPagingSource
) {

    fun calendar(): Observable<PagingData<CalendarShowEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false,
                initialLoadSize = 100
            ),
            pagingSourceFactory = { pagingSource }
        ).observable
    }
}
