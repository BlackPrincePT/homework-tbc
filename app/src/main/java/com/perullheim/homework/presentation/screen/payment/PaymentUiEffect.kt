package com.perullheim.homework.presentation.screen.payment

sealed interface PaymentUiEffect {
    data object NavigateToMyAccounts: PaymentUiEffect
    data object NavigateToFindAccount: PaymentUiEffect
}