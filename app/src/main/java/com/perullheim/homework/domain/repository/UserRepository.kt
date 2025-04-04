package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchUsersStream(): Flow<List<User>>
}