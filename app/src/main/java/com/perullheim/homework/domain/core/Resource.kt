package com.perullheim.homework.domain.core

sealed interface Resource<out D, out E : Error> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Failure<out E : Error>(val error: E) : Resource<Nothing, E>
    data object Loading : Resource<Nothing, Nothing>
}

fun <T, D, E : Error> Resource<D, E>.map(transform: (D) -> T): Resource<T, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Failure -> Resource.Failure(error)
        is Resource.Loading -> Resource.Loading
    }
}

fun Resource<*, *>.isSuccess() = this is Resource.Success
fun Resource<*, *>.isError() = this is Resource.Failure
fun Resource<*, *>.isLoading() = this is Resource.Loading