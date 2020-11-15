package com.chahine.showhive.home.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingData.Companion.insertSeparators
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    private val repository: CalendarRepository,
) : ViewModel() {

    init {
        Timber.d("CalendarViewModel#${hashCode()}")
    }

    fun myCalendar(): Flow<PagingData<CalendarModel>> {
        return repository.calendar()
            .map { pagingData -> pagingData.map { CalendarModel.Episode(it) } }
            .map { pagingData ->
                insertSeparators(pagingData) { before, after ->
                    when {
                        // we're at the end of the list
                        after == null -> null
                        // we're at the beginning of the list
                        before == null -> CalendarModel.Header(after.entry.firstAired.toLocalDate())
                        // no separator
                        before.entry.firstAired.toLocalDate() == after.entry.firstAired.toLocalDate() -> null
                        else -> CalendarModel.Header(after.entry.firstAired.toLocalDate())
                    }
                }
            }
            .cachedIn(viewModelScope)
    }
}
