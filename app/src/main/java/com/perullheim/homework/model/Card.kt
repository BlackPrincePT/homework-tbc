package com.perullheim.homework.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date
import java.util.UUID

data class Card(
    val id: UUID = UUID.randomUUID(),
    val cardholderName: String,
    val cardNumber: String,
    val expirationDate: String,
    val cvv: String
) : Parcelable {
    val type
        get() = CardType.getTypeFrom(cardNumber.firstOrNull())

    constructor(source: Parcel) : this(
        id = UUID.fromString(source.readString()),
        cardholderName = source.readString()!!,
        cardNumber = source.readString()!!,
        expirationDate = source.readString()!!,
        cvv = source.readString()!!
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id.toString())
        dest.writeString(cardholderName)
        dest.writeString(cardNumber)
        dest.writeString(expirationDate)
        dest.writeString(cvv)
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(source: Parcel): Card = Card(source)
        override fun newArray(size: Int): Array<Card?> = arrayOfNulls(size)
    }
}
