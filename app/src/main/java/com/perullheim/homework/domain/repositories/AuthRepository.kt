package com.perullheim.homework.domain.repositories

import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.model.LoginResponse
import com.perullheim.homework.data.api.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Response<LoginResponse>
    suspend fun register(registerRequest: AuthRequest): Response<RegisterResponse>
}