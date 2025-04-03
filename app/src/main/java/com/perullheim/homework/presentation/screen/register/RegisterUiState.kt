package com.perullheim.homework.presentation.screen.register

data class RegisterUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)