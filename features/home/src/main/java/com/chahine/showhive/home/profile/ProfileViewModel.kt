package com.chahine.showhive.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chahine.trakt.api.TraktApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val traktApi: TraktApi,
) : ViewModel() {

    private val _profileFlow = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)

    val profileFlow = _profileFlow.asStateFlow()

    sealed class ProfileUiState {
        object Loading : ProfileUiState()
        data class Success(val items: List<ProfileItem>) : ProfileUiState()
        data class Error(val message: String) : ProfileUiState()
    }

    fun fetchProfile() = viewModelScope.launch(Dispatchers.IO) {
        _profileFlow.emit(ProfileUiState.Loading)
        try {
            val settings = traktApi.settings()
            _profileFlow.emit(ProfileUiState.Success(listOf(ProfileItem.UserCard(settings.user))))
        } catch (exception: Exception) {
            Timber.e(exception)
            _profileFlow.emit(ProfileUiState.Error(exception.message.orEmpty()))
        }
    }
}
