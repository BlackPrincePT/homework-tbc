package com.perullheim.homework.presentation.auth

import com.perullheim.homework.presentation.Event

data class AuthState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val failure: Event<String>? = null
)