package com.perullheim.homework.presentation.screen.splash

sealed interface SplashUiEffect {
    data object NavigateToWelcome : SplashUiEffect
    data object NavigateToHome : SplashUiEffect
}