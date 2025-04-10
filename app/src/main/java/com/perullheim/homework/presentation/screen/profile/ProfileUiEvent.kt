package com.perullheim.homework.presentation.screen.profile

sealed interface ProfileUiEvent {
    data object LogOut: ProfileUiEvent
}