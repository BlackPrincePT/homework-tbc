package com.perullheim.homework.data.api

import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.model.LoginResponse
import com.perullheim.homework.data.api.model.RegisterResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(ApiConstants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: AuthRequest): Response<LoginResponse>

    @POST(ApiConstants.REGISTER_ENDPOINT)
    suspend fun register(@Body registerRequest: AuthRequest): Response<RegisterResponse>
}