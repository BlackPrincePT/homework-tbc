package com.perullheim.homework.data.repositories

import com.perullheim.homework.data.api.ResortService
import com.perullheim.homework.data.api.model.ApiResort
import com.perullheim.homework.domain.model.Resort
import com.perullheim.homework.domain.repositories.ResortsRepository
import com.perullheim.homework.utils.NetworkUtils
import com.perullheim.homework.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class ResortsRepositoryImpl @Inject constructor(
    private val resortService: ResortService
) : ResortsRepository {

    override suspend fun fetchResorts(): Resource<List<ApiResort>> {
        return NetworkUtils.handleHttpRequest(apiCall = { resortService.fetchResorts() })
    }
}