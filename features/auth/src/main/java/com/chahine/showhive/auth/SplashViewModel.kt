package com.chahine.showhive.auth

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chahine.trakt.api.TraktApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val apiClient: TraktApiClient,
) : ViewModel() {

    private val _navigateToTrakt = MutableSharedFlow<Unit>()
    private val _navigateToHome = MutableSharedFlow<Unit>()

    val navigateToTrakt = _navigateToTrakt.asSharedFlow()
    val navigateToHome = _navigateToHome.asSharedFlow()

    fun onConnectButtonClick() = viewModelScope.launch {
        _navigateToTrakt.emit(Unit)
    }

    fun onSkipButtonClick() = viewModelScope.launch {
        sharedPreferences
            .edit()
            .putBoolean("splash_skipped", true)
            .apply()
        _navigateToHome.emit(Unit)
    }

    fun onNewIntent(intent: Intent?) {
        val data = intent?.data ?: return
        val uri = Uri.parse(data.toString())
        if (uri.queryParameterNames.contains("code")) {
            val code = uri.getQueryParameter("code")
            viewModelScope.launch {
                val accessToken = apiClient.exchangeCodeForAccessToken(code!!)
                with(sharedPreferences.edit()) {
                    putString("access_token", accessToken.accessToken)
                    putString("refresh_token", accessToken.refreshToken)
                    apply()
                }

                _navigateToHome.emit(Unit)
            }
        }
    }
}
