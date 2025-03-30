package com.perullheim.homework.presentation.screen.find

import com.perullheim.homework.presentation.model.PaymentOption

sealed interface FindAccountUiEvent {
    data class OnFind(val text: String) : FindAccountUiEvent
    data class OnPaymentOptionChanged(val newOption: PaymentOption) : FindAccountUiEvent
}