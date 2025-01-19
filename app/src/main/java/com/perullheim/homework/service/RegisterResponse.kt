package com.perullheim.homework.service

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(val id: Int, val token: String)