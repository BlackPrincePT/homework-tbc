package com.perullheim.homework.domain.core

sealed interface Error

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
}

sealed interface BusinessError : Error {

    enum class Account : BusinessError {
        NOT_FOUND,
        NOT_ENOUGH_BALANCE
    }
}

sealed interface ValidationError : Error {

    enum class AccountNumber : ValidationError {
        INVALID_FORMAT,
        INVALID_SIZE,
        NOT_FOUND
    }

    enum class PersonalNumber : ValidationError {
        INVALID_FORMAT,
        INVALID_SIZE,
        NOT_FOUND
    }

    enum class MobileNumber : ValidationError {
        INVALID_FORMAT,
        INVALID_SIZE,
        NOT_FOUND
    }
}