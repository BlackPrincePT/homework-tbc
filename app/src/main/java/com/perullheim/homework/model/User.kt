package com.perullheim.homework.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
) {
    private val fullName: String
        get() = "$firstName $lastName"

    val info: String
        get() = """
            Name: $fullName
            Email: $email
        """.trimIndent()
}
