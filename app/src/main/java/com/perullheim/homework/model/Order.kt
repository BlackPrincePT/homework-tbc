package com.perullheim.homework.model

import android.os.Parcel
import android.os.Parcelable
import java.util.UUID
import kotlin.random.Random

data class Order(
    val id: UUID = UUID.randomUUID(),
    val trackingNumber: String,
    val orderNumber: Int,
    val orderItems: List<OrderItem>,
    var lastUpdatedTimestamp: Long,
    var status: OrderStatus
) : Parcelable {

    constructor(source: Parcel) : this(
        id = UUID.fromString(source.readString()),
        trackingNumber = source.readString()!!,
        orderNumber = source.readInt(),
        orderItems = source.createTypedArrayList(OrderItem.CREATOR)!!,
        lastUpdatedTimestamp = source.readLong(),
        status = OrderStatus.valueOf(source.readString()!!)
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id.toString())
        dest.writeString(trackingNumber)
        dest.writeInt(orderNumber)
        dest.writeList(orderItems)
        dest.writeLong(lastUpdatedTimestamp)
        dest.writeString(status.name)
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(source: Parcel): Order = Order(source)
        override fun newArray(size: Int): Array<Order?> = arrayOfNulls(size)
    }
}

fun generateRandomOrders(size: Int): List<Order> {
    return List(size) {
        Order(
            trackingNumber = "IK${Random.nextInt(100000000, 999999999)}",
            orderNumber = Random.nextInt(1000, 9999),
            orderItems = generateRandomItems(),
            lastUpdatedTimestamp = System.currentTimeMillis(),
            status = OrderStatus.PENDING
        )
    }
}