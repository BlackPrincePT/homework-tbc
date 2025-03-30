package com.perullheim.homework.presentation.model

import com.perullheim.homework.domain.model.CardType
import com.perullheim.homework.domain.model.Currency
import java.io.Serializable

data class UiAccount(
    val id: Int,
    val accountName: String,
    val iban: String,
    val currency: Currency,
    val cardType: CardType,
    val balance: Double,
    val cardLogoUrl: String?
) : Serializable {

    companion object {

        val DEFAULT = UiAccount(
            id = 11,
            accountName = "TBC Bank",
            iban = "GE46TB12300000000001234",
            currency = Currency.GEL,
            cardType = CardType.VISA,
            balance = 1000.00,
            cardLogoUrl = null
        )
    }
}