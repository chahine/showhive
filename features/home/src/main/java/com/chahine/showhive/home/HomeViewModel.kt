package com.chahine.showhive.home

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _navigateToSplash = MutableSharedFlow<Unit>()

    val navigateToSplash = _navigateToSplash.asSharedFlow()

    fun checkAuthStatus() = viewModelScope.launch {
        if (!sharedPreferences.contains("access_token") &&
            !sharedPreferences.getBoolean("splash_skipped", false)
        ) {
            val result = _navigateToSplash.tryEmit(Unit)
            Timber.d("checkAuthStatus() called $result")
        }
    }
}
