package com.perullheim.homework.domain.core

sealed interface DataError : Error {
    enum class Network : DataError {
        UNKNOWN,
        TIMEOUT,
        NO_CONNECTION,
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        METHOD_NOT_ALLOWED,
        CONFLICT,
        INTERNAL_SERVER_ERROR,
        BAD_GATEWAY,
        SERVICE_UNAVAILABLE,
        GATEWAY_TIMEOUT
    }
}