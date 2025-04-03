package com.perullheim.homework.domain.core

sealed interface Resource<out D, out E : Error> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Failure<out E : Error>(val error: E) : Resource<Nothing, E>
}

fun <T, D, E : Error> Resource<D, E>.map(transform: (D) -> T): Resource<T, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Failure -> Resource.Failure(error)
    }
}

fun <T, D, E : Error> Resource<List<D>, E>.mapList(transform: (D) -> T): Resource<List<T>, E> {
    return map { list ->
        list.map(transform)
    }
}

fun <D, E : Error> Resource<D, E>.onSuccess(action: (D) -> Unit): Resource<D, E> {
    if (this is Resource.Success)
        action(data)

    return this
}

fun <D, E : Error> Resource<D, E>.onFailure(action: (E) -> Unit): Resource<D, E> {
    if (this is Resource.Failure)
        action(error)

    return this
}

suspend fun <D, E : Error> Resource<D, E>.onSuccessAsync(action: suspend (D) -> Unit): Resource<D, E> {
    if (this is Resource.Success)
        action(data)

    return this
}

suspend fun <D, E : Error> Resource<D, E>.onFailureAsync(action: suspend (E) -> Unit): Resource<D, E> {
    if (this is Resource.Failure)
        action(error)

    return this
}