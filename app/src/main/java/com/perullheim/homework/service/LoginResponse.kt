package com.perullheim.homework.service

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val token: String)
