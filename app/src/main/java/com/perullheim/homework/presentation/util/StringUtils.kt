package com.perullheim.homework.presentation.util

import com.perullheim.homework.domain.model.Currency

object StringUtils {

    fun transform(iban: String): String {
        val lastFourSymbols = iban.takeLast(4)

        return "**** $lastFourSymbols"
    }

    fun transform(balance: Double, currency: Currency): String {
        return "$balance ${currency.symbol}"
    }
}