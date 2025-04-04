package com.perullheim.homework.presentation.screen.login

sealed interface LoginUiEvent {
    data class OnEmailChange(val value: String) : LoginUiEvent
    data class OnPasswordChange(val value: String) : LoginUiEvent
    data class OnRememberChange(val value: Boolean) : LoginUiEvent
    data object OnLoginClick : LoginUiEvent
}