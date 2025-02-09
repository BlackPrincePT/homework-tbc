package com.perullheim.homework.data.api

import com.perullheim.homework.data.api.model.ApiPaginatedUsers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET(ApiConstants.USERS_ENDPOINT)
    suspend fun fetchUsers(@Query(ApiConstants.Parameters.PAGE) page: Int): Response<ApiPaginatedUsers>

    companion object {

        val instance: HomeService by lazy {

            val json = Json { ignoreUnknownKeys = true }

            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_ENDPOINT)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

            retrofit.create(HomeService::class.java)
        }
    }
}