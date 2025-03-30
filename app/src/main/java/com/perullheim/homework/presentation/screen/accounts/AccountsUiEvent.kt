package com.perullheim.homework.presentation.screen.accounts

sealed interface AccountsUiEvent {
    data class OnAccountSelected(val id: Int): AccountsUiEvent
}