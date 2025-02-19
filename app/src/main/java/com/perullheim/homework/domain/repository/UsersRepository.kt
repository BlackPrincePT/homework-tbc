package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.model.pagination.Pagination
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getMoreUsers(page: Int): Pagination
    fun getUsers(): Flow<List<User>>
}