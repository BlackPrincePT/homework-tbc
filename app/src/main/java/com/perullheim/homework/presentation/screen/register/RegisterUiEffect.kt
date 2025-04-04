package com.perullheim.homework.presentation.screen.register

import com.perullheim.homework.domain.core.Error

sealed interface RegisterUiEffect {
    data class NavigateToLogin(val email: String, val password: String) : RegisterUiEffect
    data class ShowError(val error: Error) : RegisterUiEffect
}