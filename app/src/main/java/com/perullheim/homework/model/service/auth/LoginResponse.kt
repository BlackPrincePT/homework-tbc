package com.perullheim.homework.model.service.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val token: String)
