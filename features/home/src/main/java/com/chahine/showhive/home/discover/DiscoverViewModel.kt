package com.chahine.showhive.home.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.chahine.showhive.di.repo.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: DiscoverRepository,
    private val imageRepository: ImageRepository,
) : ViewModel() {

    init {
        Timber.d("DiscoverViewModel#${hashCode()}")
    }

    suspend fun trending(): Flow<PagingData<DiscoverUiModel>> {
        return repository.trending()
            .map { data ->
                data.map {
                    val posterPath = imageRepository.image(it.show.ids.tmdb)
                    DiscoverUiModel(it.show, posterPath)
                }
            }
            .cachedIn(viewModelScope)
    }
}
