package com.perullheim.homework.model

enum class CardType {
    VISA,
    MASTERCARD;

    companion object {
        fun getTypeFrom(firstNumber: Char?): CardType {
            return when (firstNumber) {
                '0', '1', '2', '3', '4' -> VISA
                else -> MASTERCARD
            }
        }
    }
}