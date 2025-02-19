package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getCurrentSession(): Flow<Session?>
    suspend fun clearUserSession()
    suspend fun login(email: String, password: String, shouldRemember: Boolean): String?
    suspend fun register(email: String, password: String): String?
}