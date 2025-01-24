package com.perullheim.homework.model.service

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val token: String)
