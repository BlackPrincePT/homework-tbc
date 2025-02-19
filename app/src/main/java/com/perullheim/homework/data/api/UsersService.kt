package com.perullheim.homework.data.api

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.api.model.ApiPaginatedUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {
    @GET(BuildConfig.USERS_ENDPOINT)
    suspend fun fetchUsers(@Query(ApiParameters.PAGE) page: Int): Response<ApiPaginatedUsers>
}