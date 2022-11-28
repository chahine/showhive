package com.chahine.showhive.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.chahine.showhive.base.util.AppManager
import com.chahine.showhive.di.repo.ImageRepository
import com.chahine.showhive.home.calendar.CalendarRepository
import com.chahine.showhive.home.calendar.CalendarUiModel
import com.chahine.showhive.home.discover.DiscoverRepository
import com.chahine.showhive.home.discover.DiscoverUiModel
import com.chahine.showhive.home.profile.ProfileItem
import com.chahine.showhive.home.util.LoadedValue
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.api.TraktTokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appManager: AppManager,
    private val traktApi: TraktApi,
    private val traktTokenManager: TraktTokenManager,
    private val calendarRepository: CalendarRepository,
    private val discoverRepository: DiscoverRepository,
    private val imageRepository: ImageRepository,
) : ViewModel() {

    private val _navigateToSplash = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = DROP_OLDEST)
    private val _profileFlow = MutableStateFlow<LoadedValue<List<ProfileItem>, Exception>>(LoadedValue.Loading)

    val navigateToSplash = _navigateToSplash.asSharedFlow()

    init {
        checkAuthStatus()
    }

    fun myCalendar(): Flow<PagingData<CalendarUiModel>> {
        return calendarRepository.calendar().map { pagingData ->
            pagingData.map {
                val posterPath = imageRepository.image(it.show.ids.tmdb)
                CalendarUiModel.Episode(it, posterPath)
            }.insertSeparators { before, after ->
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
        }.cachedIn(viewModelScope)
    }

    fun trending(): Flow<PagingData<DiscoverUiModel>> {
        return discoverRepository.trending().map { data ->
            data.map {
                val posterPath = imageRepository.image(it.show.ids.tmdb)
                DiscoverUiModel(it.show, posterPath)
            }
        }.cachedIn(viewModelScope)
    }

    fun profile(): Flow<LoadedValue<List<ProfileItem>, Exception>> = _profileFlow.asStateFlow()

    @Suppress("TooGenericExceptionCaught")
    fun fetchProfile() = viewModelScope.launch(Dispatchers.Main) {
        _profileFlow.emit(LoadedValue.Loading)
        try {
            val settings = traktApi.settings()
            _profileFlow.emit(LoadedValue.Success(listOf(ProfileItem.UserCard(settings.user))))
        } catch (exception: Exception) {
            Timber.e(exception)
            _profileFlow.emit(LoadedValue.Error(exception))
        }
    }

    fun onSignOutClick() = viewModelScope.launch(Dispatchers.Main) {
        traktTokenManager.signOut()
        _navigateToSplash.emit(Unit)
    }

    private fun checkAuthStatus() = viewModelScope.launch {
        if (!traktTokenManager.isLoggedIn && !appManager.hasSkippedSplash) {
            _navigateToSplash.emit(Unit)
        }
    }
}
