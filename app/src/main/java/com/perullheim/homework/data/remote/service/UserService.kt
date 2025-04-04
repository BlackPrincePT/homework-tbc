package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.core.ApiConstants
import com.perullheim.homework.data.remote.model.PaginatedUsersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET(BuildConfig.USERS_ENDPOINT)
    suspend fun fetchUsers(@Query(ApiConstants.PAGE) page: Int): Response<PaginatedUsersDto>
}