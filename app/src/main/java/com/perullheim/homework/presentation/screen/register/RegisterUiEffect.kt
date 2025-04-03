package com.perullheim.homework.presentation.screen.register

import com.perullheim.homework.domain.core.Error

sealed interface RegisterUiEffect {
    data class ShowError(val error: Error) : RegisterUiEffect
    data object NavigateToLogin : RegisterUiEffect
}