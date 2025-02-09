package com.perullheim.homework.utils

import retrofit2.Response

object NetworkUtils {

    suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Resource<T> {
        val response = apiCall.invoke()
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(data = it)
                } ?: Resource.Error(message = response.message())
            } else {
                Resource.Error(message = response.message())
            }
        } catch (throwable: Throwable) {
            Resource.Error(message = throwable.message.orEmpty())
        }
    }
}