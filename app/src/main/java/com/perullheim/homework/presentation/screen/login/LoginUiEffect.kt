package com.perullheim.homework.presentation.screen.login

import com.perullheim.homework.domain.core.Error

sealed interface LoginUiEffect {
    data class ShowError(val error: Error): LoginUiEffect
    data object NavigateToHome: LoginUiEffect
}