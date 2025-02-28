package com.perullheim.homework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    @SerialName("lat") val lat: Double,
    @SerialName("lan") val lan: Double,
    @SerialName("title") val title: String,
    @SerialName("address") val address: String
)