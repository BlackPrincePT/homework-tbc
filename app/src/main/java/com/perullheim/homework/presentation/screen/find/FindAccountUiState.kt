package com.perullheim.homework.presentation.screen.find

import com.perullheim.homework.presentation.model.PaymentOption

data class FindAccountUiState(
    val selectedOption: PaymentOption = PaymentOption.ACCOUNT_NUMBER
)