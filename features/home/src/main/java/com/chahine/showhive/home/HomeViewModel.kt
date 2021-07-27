package com.chahine.showhive.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chahine.showhive.base.util.AppManager
import com.chahine.trakt.api.TraktTokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appManager: AppManager,
    private val traktTokenManager: TraktTokenManager,
) : ViewModel() {

    private val _navigateToSplash = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val navigateToSplash = _navigateToSplash.asSharedFlow()

    init {
        checkAuthStatus()
    }

    internal fun checkAuthStatus() = viewModelScope.launch {
        if (!traktTokenManager.isLoggedIn && !appManager.hasSkippedSplash) {
            _navigateToSplash.emit(Unit)
        }
    }
}
