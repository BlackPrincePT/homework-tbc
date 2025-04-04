package com.perullheim.homework.presentation.screen.welcome

sealed interface WelcomeUiEffect {
    data object NavigateToRegister : WelcomeUiEffect
    data object NavigateToLogin : WelcomeUiEffect
}