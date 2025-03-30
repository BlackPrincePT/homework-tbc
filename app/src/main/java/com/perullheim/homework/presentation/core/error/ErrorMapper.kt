package com.perullheim.homework.presentation.core.error

import com.perullheim.homework.R
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Error

fun Error.toStringResId(): Int = when (this) {
    is DataError -> this.toStringResId()
}

private fun DataError.toStringResId(): Int = when (this) {
    is DataError.Network -> this.toStringResId()
}

private fun DataError.Network.toStringResId(): Int = when (this) {
    DataError.Network.NO_CONNECTION -> R.string.error_no_connection
    DataError.Network.TIMEOUT -> R.string.error_timeout
    DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized
    DataError.Network.FORBIDDEN -> R.string.error_forbidden
    DataError.Network.NOT_FOUND -> R.string.error_not_found
    else -> R.string.error_generic
}