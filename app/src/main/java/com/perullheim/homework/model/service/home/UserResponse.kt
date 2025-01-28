package com.perullheim.homework.model.service.home

import com.perullheim.homework.model.data.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val total: Int,
    @SerialName("total_pages")val totalPages: Int,
    val data: List<UserDto>
)