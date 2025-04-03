package com.perullheim.homework.presentation.screen.login

import com.perullheim.homework.domain.core.ValidationError

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val remember: Boolean = false,
    val emailError: ValidationError? = null,
    val passwordError: ValidationError? = null,
    val isLoading: Boolean = false
)