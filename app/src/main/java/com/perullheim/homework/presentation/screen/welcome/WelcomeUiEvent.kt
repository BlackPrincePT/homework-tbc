package com.perullheim.homework.presentation.screen.welcome

sealed interface WelcomeUiEvent {
    data object OnRegisterClick : WelcomeUiEvent
    data object OnLoginClick : WelcomeUiEvent
}