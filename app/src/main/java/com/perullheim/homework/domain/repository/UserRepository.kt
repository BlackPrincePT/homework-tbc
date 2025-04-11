package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchUsersStream(): Flow<List<User>>
    suspend fun cacheMoreUsers(): Resource<Unit, DataError.Network>
    suspend fun clearCache()
}