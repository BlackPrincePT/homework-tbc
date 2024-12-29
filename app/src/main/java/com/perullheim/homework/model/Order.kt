package com.perullheim.homework.model

import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val trackingNumber: String,
    val orderNumber: Int,
    val orderItems: List<OrderItem>,
    var lastUpdatedTimestamp: Long,
    var status: OrderStatus
)