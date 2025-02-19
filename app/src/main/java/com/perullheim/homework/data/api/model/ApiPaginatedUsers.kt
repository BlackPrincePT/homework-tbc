package com.perullheim.homework.data.api.model

import com.perullheim.homework.domain.model.pagination.PaginatedUsers
import com.perullheim.homework.domain.model.pagination.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPaginatedUsers(
    @SerialName("page") val currentPage: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("data") val users: List<ApiUser>
) {

    fun toDomain(): PaginatedUsers {
        return PaginatedUsers(
            users = users.map(ApiUser::toDomain),
            pagination = Pagination(
                currentPage = currentPage,
                totalPages = totalPages
            )
        )
    }
}