package com.perullheim.homework.presentation.screen.accounts

import com.perullheim.homework.domain.model.Account

data class AccountsUiState(
    val accounts: List<Account> = emptyList()
)