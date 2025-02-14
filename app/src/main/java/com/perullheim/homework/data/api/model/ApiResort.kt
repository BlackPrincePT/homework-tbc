package com.perullheim.homework.data.api.model

import com.perullheim.homework.domain.model.Resort
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResort(
    @SerialName("id") val id: Int,
    @SerialName("cover") val coverUrl: String,
    @SerialName("price") val price: String,
    @SerialName("title") val title: String,
    @SerialName("location") val location: String,
    @SerialName("reaction_count") val reactionCount: Int,
    @SerialName("rate") val rating: Int?
) {

    fun toDomain(): Resort {
        return Resort(
            id = id,
            coverUrl = coverUrl,
            price = price,
            title = title,
            location = location,
            reactionCount = reactionCount,
            rating = if (rating != null && rating in 0..5) rating else 0
        )
    }
}
