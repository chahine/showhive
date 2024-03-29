package com.chahine.showhive.home.calendar

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepository @Inject constructor(
    private val pagingSource: CalendarPagingSource,
) {

    fun calendar(): Flow<PagingData<CalendarUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}
