package com.perullheim.homework.data.di

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.AddressService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideAddressService(builder: Retrofit.Builder): AddressService {
        return builder
            .build()
            .create(AddressService::class.java)
    }


    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        val json = Json { ignoreUnknownKeys = true }

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

        return retrofit
    }
}