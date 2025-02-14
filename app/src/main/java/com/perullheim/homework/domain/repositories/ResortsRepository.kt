package com.perullheim.homework.domain.repositories

import com.perullheim.homework.data.api.model.ApiResort
import com.perullheim.homework.domain.model.Resort
import com.perullheim.homework.utils.Resource
import retrofit2.Response

interface ResortsRepository {
    suspend fun fetchResorts(): Resource<List<ApiResort>>
}