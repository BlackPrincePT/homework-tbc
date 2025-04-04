package com.perullheim.homework.presentation.screen.register

sealed interface RegisterUiEvent {
    data class OnEmailChange(val value: String) : RegisterUiEvent
    data class OnUsernameChange(val value: String) : RegisterUiEvent
    data class OnPasswordChange(val value: String) : RegisterUiEvent
    data object OnRegisterClick : RegisterUiEvent
}