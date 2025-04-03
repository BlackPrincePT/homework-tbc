package com.perullheim.homework.presentation.screen.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)