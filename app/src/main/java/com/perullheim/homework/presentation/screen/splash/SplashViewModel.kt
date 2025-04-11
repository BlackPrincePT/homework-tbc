package com.perullheim.homework.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.usecase.session.CheckSavedSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val hasSavedSession: CheckSavedSessionUseCase
) : ViewModel() {

    private val _uiEffect = MutableSharedFlow<SplashUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        checkSavedSession()
    }

    private fun checkSavedSession() = viewModelScope.launch {
        if (hasSavedSession().also(::println))
            sendEffect(SplashUiEffect.NavigateToHome)
        else
            sendEffect(SplashUiEffect.NavigateToWelcome)
    }

    private fun sendEffect(effect: SplashUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}