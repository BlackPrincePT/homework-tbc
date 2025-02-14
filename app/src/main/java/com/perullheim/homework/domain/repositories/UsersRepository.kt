package com.perullheim.homework.domain.repositories

import com.perullheim.homework.data.api.model.ApiPaginatedUsers
import retrofit2.Response

interface UsersRepository {
    suspend fun fetchUsers(page: Int): Response<ApiPaginatedUsers>
}