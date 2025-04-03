package com.perullheim.homework.presentation.screen.login

sealed interface LoginUiEvent {
    data object OnLoginClick : LoginUiEffect
}