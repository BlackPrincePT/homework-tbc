package com.perullheim.homework.presentation.screen.payment

import com.perullheim.homework.presentation.model.UiAccount

sealed interface PaymentUiEvent {
    data class OnAccountSelected(val account: UiAccount): PaymentUiEvent
    data class OnAccountFound(val id: String): PaymentUiEvent
    data class OnAmountMyCurrencyChanged(val value: String): PaymentUiEvent
    data class OnAmountTheirCurrencyChanged(val value: String): PaymentUiEvent
    data object OnContinueClick: PaymentUiEvent
    data object OnFromAccountClick: PaymentUiEvent
    data object OnToAccountClick: PaymentUiEvent
    data object OnNavigateBack: PaymentUiEvent
}