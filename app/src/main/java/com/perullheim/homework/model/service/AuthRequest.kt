package com.perullheim.homework.model.service

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(val email: String, val password: String)