package com.perullheim.homework.domain.model

enum class CardType {
    VISA,
    MASTER_CARD;

    val displayName: String
        get() = when (this) {
            VISA -> "VISA"
            MASTER_CARD -> "Mastercard"
        }
}