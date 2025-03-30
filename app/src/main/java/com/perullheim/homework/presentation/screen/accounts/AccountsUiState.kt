package com.perullheim.homework.presentation.screen.accounts

import com.perullheim.homework.presentation.model.UiAccount

data class AccountsUiState(
    val accounts: List<UiAccount> = emptyList()
)