package com.perullheim.homework.data.di

import com.perullheim.homework.data.api.ApiConstants
import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.data.api.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        val json = Json { ignoreUnknownKeys = true }

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersService(): UsersService {
        val json = Json { ignoreUnknownKeys = true }

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(UsersService::class.java)
    }
}