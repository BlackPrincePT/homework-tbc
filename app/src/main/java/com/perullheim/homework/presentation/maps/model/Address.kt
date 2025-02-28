package com.perullheim.homework.presentation.maps.model

import android.os.Parcelable
import com.perullheim.homework.data.remote.model.AddressDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val lat: Double,
    val lan: Double,
    val title: String,
    val address: String
): Parcelable {

    val info: String
        get() = """
            Position: $lat, $lan
            Title: $title
            Address: $address
        """.trimIndent()

    companion object {
        fun fromDto(dto: AddressDto): Address {
            return Address(
                lan = dto.lan,
                lat = dto.lat,
                title = dto.title,
                address = dto.address
            )
        }
    }
}