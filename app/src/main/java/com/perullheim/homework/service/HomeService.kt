package com.perullheim.homework.service

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

interface HomeService {
    @GET("v3/f3f41821-7434-471f-9baa-ae3dee984e6d")
    suspend fun fetchUsers() : Response<UserResponse>

    companion object {

        val INSTANCE: HomeService by lazy {

            val json = Json { ignoreUnknownKeys = true }

            val retrofit = Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

            retrofit.create(HomeService::class.java)
        }
    }
}