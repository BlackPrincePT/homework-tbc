package com.perullheim.homework.presentation.payment

sealed interface PaymentUiEvent {
    data class OnAccountSelected(val id: String): PaymentUiEvent
    data object OnNavigateBack: PaymentUiEvent
}