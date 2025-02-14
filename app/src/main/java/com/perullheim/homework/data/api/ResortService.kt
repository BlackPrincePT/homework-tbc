package com.perullheim.homework.data.api

import com.perullheim.homework.data.api.model.ApiResort
import retrofit2.Response
import retrofit2.http.GET

interface ResortService {
    @GET(ApiConstants.RESORTS_ENDPOINT)
    suspend fun fetchResorts(): Response<List<ApiResort>>
}