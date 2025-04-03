package com.perullheim.homework.presentation.screen.login

sealed interface LoginUiEvent {
    data class OnUsernameChange(val value: String): LoginUiEvent
    data class OnPasswordChange(val value: String): LoginUiEvent
    data object OnLoginClick : LoginUiEvent
}