package com.perullheim.homework.domain.model

data class Account(
    val id: Int,
    val accountName: String,
    val currency: Currency,
    val cardType: CardType,
    val balance: Int,
    val cardLogoUrl: String?
)