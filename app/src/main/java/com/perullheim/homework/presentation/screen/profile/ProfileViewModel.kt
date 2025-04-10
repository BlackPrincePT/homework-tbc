package com.perullheim.homework.presentation.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.usecase.ClearSessionUseCase
import com.perullheim.homework.domain.usecase.GetCurrentSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearSession: ClearSessionUseCase,
    currentSession: GetCurrentSessionUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    private val _uiEffect = MutableSharedFlow<ProfileUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        updateState { copy(currentSession = currentSession()) }
    }

    fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.LogOut -> viewModelScope.launch {
                clearSession()
                sendEffect(ProfileUiEffect.LoggedOutSuccessfully)
            }
        }
    }

    private fun updateState(change: ProfileUiState.() -> ProfileUiState) {
        uiState = uiState.change()
    }

    private fun sendEffect(effect: ProfileUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}