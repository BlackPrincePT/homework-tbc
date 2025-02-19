package com.perullheim.homework.data.api.model

import com.perullheim.homework.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: Int,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val avatar: String
) {

    fun toDomain(): User {
        return User(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }
}