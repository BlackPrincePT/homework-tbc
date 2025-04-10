package com.perullheim.homework.presentation.screen.home

import com.perullheim.homework.domain.model.User

data class HomeUiState(
    val users: List<User> = emptyList()
)