package com.perullheim.homework.model

import kotlin.random.Random

data class OrderItem(
    val price: Int,
    val description: String
)

fun generateRandomItems(): List<OrderItem> {
    val descriptions = listOf(
        "Laptop",
        "Phone",
        "Headphones",
        "Charger",
        "Monitor",
        "Keyboard",
        "Mouse",
        "Backpack",
        "Notebook",
        "Pen"
    )

    val itemCount = Random.nextInt(1, 8)
    return List(itemCount) {
        OrderItem(
            price = Random.nextInt(100, 5000),
            description = descriptions.random()
        )
    }
}