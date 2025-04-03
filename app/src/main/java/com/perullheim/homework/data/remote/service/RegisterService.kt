package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.RegisterRequest
import com.perullheim.homework.data.remote.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST(BuildConfig.REGISTER_ENDPOINT)
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}