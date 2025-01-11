package com.perullheim.homework.model

import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val furniture: Furniture,
    val quantity: Int,
    var deliveryStatus: DeliveryStatus = DeliveryStatus.ACTIVE,
    var feedback: Feedback? = null
) {
    val totalPrice: Double
        get() = furniture.price * quantity

    companion object {
        val exampleOrders = mutableListOf(
            Order(
                furniture = Furniture.exampleFurniture[0],
                quantity = 3,
                deliveryStatus = DeliveryStatus.COMPLETED
            ),
            Order(
                furniture = Furniture.exampleFurniture[1],
                quantity = 2,
                deliveryStatus = DeliveryStatus.COMPLETED
            ),
            Order(
                furniture = Furniture.exampleFurniture[2],
                quantity = 4,
            ),
            Order(
                furniture = Furniture.exampleFurniture[3],
                quantity = 1,
            ),
            Order(
                furniture = Furniture.exampleFurniture[4],
                quantity = 2,
            ),
        )
    }
}
