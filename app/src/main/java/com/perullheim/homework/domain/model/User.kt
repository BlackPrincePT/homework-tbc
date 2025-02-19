package com.perullheim.homework.domain.model

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {
    val fullName: String
        get() = "$firstName $lastName"
}