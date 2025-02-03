package com.perullheim.homework.service

import com.perullheim.homework.model.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val status: Boolean,
    val users: List<UserDto>
)