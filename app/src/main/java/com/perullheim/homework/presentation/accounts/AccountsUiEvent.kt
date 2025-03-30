package com.perullheim.homework.presentation.accounts

sealed interface AccountsUiEvent {
    data class OnAccountSelected(val id: Int): AccountsUiEvent
}