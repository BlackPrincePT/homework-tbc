package com.perullheim.homework.model.service.home

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("api/users")
    suspend fun fetchUsers(@Query("page") page: Int): Response<UserResponse>

    companion object {

        val instance: HomeService by lazy {

            val json = Json { ignoreUnknownKeys = true }

            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

            retrofit.create(HomeService::class.java)
        }
    }
}