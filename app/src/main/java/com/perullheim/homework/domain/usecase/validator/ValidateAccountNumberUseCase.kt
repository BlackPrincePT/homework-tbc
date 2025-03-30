package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

private val accountNumberRegex = Regex(pattern = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{17}$")

class ValidateAccountNumberUseCase @Inject constructor() {

    operator fun invoke(accountNumber: String): Resource<Unit, ValidationError.AccountNumber> {
        if (accountNumber.length != 23)
            return Resource.Failure(error = ValidationError.AccountNumber.INVALID_SIZE)

        if (!accountNumberRegex.matches(accountNumber))
            return Resource.Failure(error = ValidationError.AccountNumber.INVALID_FORMAT)

        return Resource.Success(Unit)
    }
}