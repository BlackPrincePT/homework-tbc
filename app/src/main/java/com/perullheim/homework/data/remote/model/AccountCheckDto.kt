package com.perullheim.homework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountCheckDto(
    @SerialName("status") val status: String
) {

    @Serializable
    data class Request(
        @SerialName("account_number") val accountNumber: String
    )

    enum class Status {
        Success,
        Failure;

        val value: Boolean
            get() = when(this) {
                Success -> true
                Failure -> false
            }
    }
}