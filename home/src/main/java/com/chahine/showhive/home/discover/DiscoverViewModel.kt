package com.chahine.showhive.home.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.rxjava3.cachedIn
import timber.log.Timber
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val repository: DiscoverRepository,
) : ViewModel() {

    init {
        Timber.d("DiscoverViewModel#${hashCode()} ")
    }

    fun requestTrendingShows() = repository.trending().cachedIn(viewModelScope)
}
