package com.perullheim.homework.model.service

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/login")
    suspend fun login(@Body loginRequest: AuthRequest) : Response<LoginResponse>

    @POST("api/register")
    suspend fun register(@Body registerRequest: AuthRequest) : Response<RegisterResponse>

    companion object {

        val instance: AuthService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()

            retrofit.create(AuthService::class.java)
        }
    }
}