package com.perullheim.homework.presentation.model

enum class PaymentOption {
    ACCOUNT_NUMBER,
    PERSONAL_NUMBER,
    MOBILE_NUMBER;

    val displayName: String
        get() = when (this) {
            ACCOUNT_NUMBER -> "IBAN"
            PERSONAL_NUMBER -> "Personal Number"
            MOBILE_NUMBER -> "Mobile Number"
        }
}