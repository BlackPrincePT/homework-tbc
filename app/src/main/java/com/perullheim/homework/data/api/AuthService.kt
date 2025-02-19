package com.perullheim.homework.data.api

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(BuildConfig.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: AuthRequest): Response<AuthResponse>

    @POST(BuildConfig.REGISTER_ENDPOINT)
    suspend fun register(@Body registerRequest: AuthRequest): Response<AuthResponse>
}