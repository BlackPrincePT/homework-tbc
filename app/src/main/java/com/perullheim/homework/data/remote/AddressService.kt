package com.perullheim.homework.data.remote

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.AddressDto
import retrofit2.Response
import retrofit2.http.GET

interface AddressService {
    @GET(BuildConfig.ADDRESS_ENDPOINT)
    suspend fun fetchAddresses(): Response<List<AddressDto>>
}