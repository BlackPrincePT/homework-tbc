package com.perullheim.homework.domain.core

sealed interface Error

sealed interface DataError: Error {

    enum class Network : DataError {
        NO_CONNECTION,          // Show a message like "No internet connection."
        TIMEOUT,                // Inform the user the request took too long.
        BAD_REQUEST,            // Useful for validation errors.
        UNAUTHORIZED,           // Prompt for re-login or permission issues.
        FORBIDDEN,              // Notify the user they lack permissions.
        NOT_FOUND,              // Indicate that a resource could not be found.
        INTERNAL_SERVER_ERROR,  // Display a generic error message.
        SERVICE_UNAVAILABLE,    // Inform the user that the service is temporarily down.
        UNKNOWN                 // Catch-all for unspecified network errors.
    }
}