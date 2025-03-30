package com.perullheim.homework.presentation.screen.payment

import com.perullheim.homework.presentation.model.UiAccount

data class PaymentUiState(
    val myAccount: UiAccount? = null,
    val accountToPay: UiAccount? = null,
    val amountMyCurrency: Int = 0,
    val amountTheirCurrency: Int = 0
)