package com.perullheim.homework.presentation.screen.accounts

import com.perullheim.homework.presentation.model.UiAccount

sealed interface AccountsUiEvent {
    data class OnAccountSelected(val account: UiAccount): AccountsUiEvent
}