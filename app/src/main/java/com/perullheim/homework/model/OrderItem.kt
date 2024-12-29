package com.perullheim.homework.model

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

data class OrderItem(
    val price: Int,
    val description: String
) : Parcelable {

    constructor(source: Parcel) : this(source.readInt(), source.readString()!!)

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(price)
        dest.writeString(description)
    }

    companion object CREATOR: Parcelable.Creator<OrderItem> {
        override fun createFromParcel(soruce: Parcel): OrderItem = OrderItem(soruce)
        override fun newArray(size: Int): Array<OrderItem?> = arrayOfNulls(size)
    }
}

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
            price = Random.nextInt(10, 200),
            description = descriptions.random()
        )
    }
}