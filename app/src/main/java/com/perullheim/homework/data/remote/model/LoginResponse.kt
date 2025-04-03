package com.perullheim.homework.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)