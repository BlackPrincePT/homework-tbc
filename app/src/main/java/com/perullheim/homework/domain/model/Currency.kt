package com.perullheim.homework.domain.model

enum class Currency {
    GEL,
    USD,
    EUR;

    val symbol: Char
        get() = when (this) {
            GEL -> '₾'
            USD -> '$'
            EUR -> '€'
        }
}