package com.perullheim.homework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("id") val id: Int,
    @SerialName("account_name") val accountName: String,
    @SerialName("account_number") val accountNumber: String,
    @SerialName("valute_type") val currency: String,
    @SerialName("card_type") val cardType: String,
    @SerialName("balance") val balance: Int,
    @SerialName("card_logo") val cardLogoUrl: String?
)