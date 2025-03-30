package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

class ValidatePersonalNumberUseCase @Inject constructor() {

    operator fun invoke(personalNumber: String): Resource<Unit, ValidationError.PersonalNumber> {
        if (personalNumber.length != 11)
            return Resource.Failure(error = ValidationError.PersonalNumber.INVALID_SIZE)

        if (personalNumber.all { it.isDigit().not() })
            return Resource.Failure(error = ValidationError.PersonalNumber.INVALID_FORMAT)

        return Resource.Success(Unit)
    }
}