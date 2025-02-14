package com.perullheim.homework.data.repositories

import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.model.LoginResponse
import com.perullheim.homework.data.api.model.RegisterResponse
import com.perullheim.homework.domain.repositories.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(loginRequest: AuthRequest): Response<LoginResponse> {
        return authService.login(loginRequest)
    }

    override suspend fun register(registerRequest: AuthRequest): Response<RegisterResponse> {
        return authService.register(registerRequest)
    }
}