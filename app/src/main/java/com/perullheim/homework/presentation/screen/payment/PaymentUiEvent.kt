package com.perullheim.homework.presentation.screen.payment

sealed interface PaymentUiEvent {
    data class OnAccountSelected(val id: String): PaymentUiEvent
    data object OnNavigateBack: PaymentUiEvent
}