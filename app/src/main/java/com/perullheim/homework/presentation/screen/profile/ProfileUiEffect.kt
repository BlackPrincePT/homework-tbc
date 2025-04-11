package com.perullheim.homework.presentation.screen.profile

sealed interface ProfileUiEffect {
    data object LoggedOutSuccessfully: ProfileUiEffect
}