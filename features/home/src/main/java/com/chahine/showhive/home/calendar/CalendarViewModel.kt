package com.chahine.showhive.home.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.chahine.showhive.di.repo.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: CalendarRepository,
    private val imageRepository: ImageRepository,
) : ViewModel() {

    init {
        Timber.d("CalendarViewModel#${hashCode()}")
    }

    fun myCalendar(): Flow<PagingData<CalendarUiModel>> {
        return repository.calendar()
            .map { pagingData ->
                pagingData
                    .map {
                        val posterPath = imageRepository.image(it.show.ids.tmdb)
                        CalendarUiModel.Episode(it, posterPath)
                    }
                    .insertSeparators { before, after ->
                        when {
                            // we're at the end of the list
                            after == null -> null
                            // we're at the beginning of the list
                            before == null -> CalendarUiModel.Header(after.entry.firstAired.toLocalDate())
                            // no separator
                            before.entry.firstAired.toLocalDate() == after.entry.firstAired.toLocalDate() -> null
                            else -> CalendarUiModel.Header(after.entry.firstAired.toLocalDate())
                        }
                    }
            }
            .cachedIn(viewModelScope)
    }
}
