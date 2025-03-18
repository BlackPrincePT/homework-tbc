package com.perullheim.homework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipmentDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("name_de") val nameDe: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("bg1_number") val bg1Number: String? = null,
    @SerialName("bg1_variant") val bg1Variant: String? = null,
    @SerialName("order_id") val orderId: Int? = null,
    @SerialName("main") val main: String?,
    @SerialName("children") val children: List<EquipmentDto> = emptyList()
)