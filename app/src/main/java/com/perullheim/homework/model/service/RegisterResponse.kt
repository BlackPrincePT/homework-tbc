package com.perullheim.homework.model.service

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(val id: Int, val token: String)