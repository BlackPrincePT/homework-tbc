package com.perullheim.homework.presentation.screen.payment

import com.perullheim.homework.domain.core.Error

sealed interface PaymentUiEffect {
    data class Failure(val error: Error): PaymentUiEffect
    data object NavigateToMyAccounts: PaymentUiEffect
    data object NavigateToFindAccount: PaymentUiEffect
}