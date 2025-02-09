package com.perullheim.homework.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
