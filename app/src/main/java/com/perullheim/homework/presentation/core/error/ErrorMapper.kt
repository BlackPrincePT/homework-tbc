package com.perullheim.homework.presentation.core.error

import com.perullheim.homework.R
import com.perullheim.homework.domain.core.BusinessError
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Error
import com.perullheim.homework.domain.core.ValidationError

fun Error.toStringResId(): Int = when (this) {
    is DataError -> this.toStringResId()
    is BusinessError -> this.toStringResId()
    is ValidationError -> this.toStringResId()
}

private fun DataError.toStringResId(): Int = when (this) {
    is DataError.Network -> this.toStringResId()
}

private fun BusinessError.toStringResId(): Int = when (this) {
    is BusinessError.Account -> this.toStringResId()
}

private fun ValidationError.toStringResId(): Int = when (this) {
    is ValidationError.AccountNumber -> this.toStringResId()
    is ValidationError.PersonalNumber -> this.toStringResId()
    is ValidationError.MobileNumber -> this.toStringResId()
}

private fun DataError.Network.toStringResId(): Int = when (this) {
    DataError.Network.NO_CONNECTION -> R.string.error_no_connection
    DataError.Network.TIMEOUT -> R.string.error_timeout
    DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized
    DataError.Network.FORBIDDEN -> R.string.error_forbidden
    DataError.Network.NOT_FOUND -> R.string.error_not_found
    else -> R.string.error_generic
}

private fun BusinessError.Account.toStringResId(): Int = when (this) {
    BusinessError.Account.NOT_FOUND -> R.string.error_account_not_found
    BusinessError.Account.NOT_ENOUGH_BALANCE -> R.string.error_account_not_enough_balance
}

private fun ValidationError.AccountNumber.toStringResId(): Int = when (this) {
    ValidationError.AccountNumber.INVALID_FORMAT -> R.string.error_account_number_invalid_format
    ValidationError.AccountNumber.INVALID_SIZE -> R.string.error_account_number_invalid_size
    ValidationError.AccountNumber.NOT_FOUND -> R.string.error_account_number_not_found
}

private fun ValidationError.PersonalNumber.toStringResId(): Int = when (this) {
    ValidationError.PersonalNumber.INVALID_FORMAT -> R.string.error_personal_number_invalid_format
    ValidationError.PersonalNumber.INVALID_SIZE -> R.string.error_personal_number_invalid_size
    ValidationError.PersonalNumber.NOT_FOUND -> R.string.error_personal_number_not_found
}

private fun ValidationError.MobileNumber.toStringResId(): Int = when (this) {
    ValidationError.MobileNumber.INVALID_FORMAT -> R.string.error_mobile_number_invalid_format
    ValidationError.MobileNumber.INVALID_SIZE -> R.string.error_mobile_number_invalid_size
    ValidationError.MobileNumber.NOT_FOUND -> R.string.error_mobile_number_not_found
}
