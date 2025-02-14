package com.perullheim.homework.domain.repositories

import kotlinx.coroutines.flow.Flow

interface UserSessionRepository {
    val userToken: Flow<String?>
    suspend fun saveUserToken(token: String)
    suspend fun deleteUserToken()
}