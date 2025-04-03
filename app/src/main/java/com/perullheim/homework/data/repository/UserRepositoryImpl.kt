package com.perullheim.homework.data.repository

import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun fetchUsersStream(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}