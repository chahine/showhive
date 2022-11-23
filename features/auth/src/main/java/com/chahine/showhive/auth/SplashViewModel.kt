package com.chahine.showhive.auth

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chahine.showhive.base.util.AppManager
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.api.TraktTokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appManager: AppManager,
    private val traktTokenManager: TraktTokenManager,
    private val apiClient: TraktApiClient,
) : ViewModel() {

    companion object {
        private const val KEY_CODE = "code"
    }

    private val _navigateToTrakt = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val _navigateToHome = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val navigateToTrakt: SharedFlow<Unit> = _navigateToTrakt.asSharedFlow()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome.asSharedFlow()

    fun onConnectButtonClick() = viewModelScope.launch(Dispatchers.Main) {
        _navigateToTrakt.emit(Unit)
    }

    fun onSkipButtonClick() = viewModelScope.launch(Dispatchers.Main) {
        appManager.hasSkippedSplash = true
        _navigateToHome.emit(Unit)
    }

    fun onNewIntent(intent: Intent?) = viewModelScope.launch(Dispatchers.IO) {
        val data = intent?.data ?: return@launch
        val uri = Uri.parse(data.toString())
        val code = uri.getQueryParameter(KEY_CODE) ?: return@launch
        val accessToken = apiClient.exchangeCodeForAccessToken(code)

        traktTokenManager.saveTokens(accessToken)
        _navigateToHome.emit(Unit)
    }
}
