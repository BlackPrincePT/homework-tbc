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

sealed interface ValidationError : Error {

    enum class Email : ValidationError {
        EMPTY,
        INVALID
    }

    enum class Username : ValidationError {
        EMPTY,
        TOO_SHORT
    }

    enum class Password : ValidationError {
        EMPTY,
        TOO_SHORT
    }
}