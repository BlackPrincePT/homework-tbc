package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.EquipmentDto
import retrofit2.Response
import retrofit2.http.GET

interface EquipmentService {
    @GET(BuildConfig.EQUIPMENT_ENDPOINT)
    suspend fun fetchEquipment(): Response<List<EquipmentDto>>
}