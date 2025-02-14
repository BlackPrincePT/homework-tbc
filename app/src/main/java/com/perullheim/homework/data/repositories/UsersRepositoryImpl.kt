package com.perullheim.homework.data.repositories

import com.perullheim.homework.data.api.UsersService
import com.perullheim.homework.data.api.model.ApiPaginatedUsers
import com.perullheim.homework.domain.repositories.UsersRepository
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersService: UsersService
) : UsersRepository {

    override suspend fun fetchUsers(page: Int): Response<ApiPaginatedUsers> {
        return usersService.fetchUsers(page)
    }
}