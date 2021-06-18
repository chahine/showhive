package com.chahine.showhive.home

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _navigateToSplash = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = DROP_OLDEST)

    val navigateToSplash = _navigateToSplash.asSharedFlow()

    init {
        checkAuthStatus()
    }

    internal fun checkAuthStatus() = viewModelScope.launch {
        if (!sharedPreferences.contains("access_token") &&
            !sharedPreferences.getBoolean("splash_skipped", false)
        ) {
            _navigateToSplash.emit(Unit)
        }
    }
}
