package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.LoginRequest
import com.perullheim.homework.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(BuildConfig.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}