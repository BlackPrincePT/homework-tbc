package com.perullheim.homework.data.api

import com.perullheim.homework.data.api.model.Resource
import retrofit2.Response
import javax.inject.Inject

class NetworkUtils @Inject constructor() {

    suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall.invoke()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Resource.Success(data = body)
            } else {
                Resource.Error(message = "Error: ${response.code()}")
            }
        } catch (throwable: Throwable) {
            Resource.Error(message = throwable.message.orEmpty())
        }
    }
}