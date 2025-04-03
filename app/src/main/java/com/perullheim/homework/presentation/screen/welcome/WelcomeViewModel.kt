package com.perullheim.homework.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private val _uiEffect = MutableSharedFlow<WelcomeUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: WelcomeUiEvent) {
        when (event) {
            WelcomeUiEvent.OnLoginClick -> sendEffect(WelcomeUiEffect.NavigateToLogin)
            WelcomeUiEvent.OnRegisterClick -> sendEffect(WelcomeUiEffect.NavigateToRegister)
        }
    }

    private fun sendEffect(effect: WelcomeUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}