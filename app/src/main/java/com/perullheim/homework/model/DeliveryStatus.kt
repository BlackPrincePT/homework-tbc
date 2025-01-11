package com.perullheim.homework.model

enum class DeliveryStatus(val filterDisplayName: String, val itemDisplayName: String) {
    ACTIVE(filterDisplayName = "Active", itemDisplayName = "In Delivery"),
    COMPLETED(filterDisplayName = "Completed", itemDisplayName = "Completed")
}