package com.perullheim.homework.service

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(val email: String, val password: String)