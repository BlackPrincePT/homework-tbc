package com.perullheim.homework.presentation.screen.register

import com.perullheim.homework.domain.core.ValidationError

data class RegisterUiState(
    val isLoading: Boolean = false,

    // Compose Values
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val emailError: ValidationError? = null,
    val usernameError: ValidationError? = null,
    val passwordError: ValidationError? = null
)