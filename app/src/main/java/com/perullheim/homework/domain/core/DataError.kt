package com.perullheim.homework.domain.core

sealed interface DataError : Error {
    enum class Network : DataError {

    }
    enum class Local : DataError {

    }
}