package com.perullheim.homework.domain.core

interface Error

sealed interface DataError : Error {

    enum class Network : DataError {
        NO_CONNECTION,
        TIMEOUT,
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        SERVICE_UNAVAILABLE,
        UNKNOWN
    }

    enum class Local : DataError {

    }
}