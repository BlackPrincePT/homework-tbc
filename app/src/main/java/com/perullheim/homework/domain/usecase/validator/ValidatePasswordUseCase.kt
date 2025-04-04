package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): Resource<Unit, ValidationError.Password> {
        if (password.isEmpty())
            return Resource.Failure(error = ValidationError.Password.EMPTY)

        if (password.length < 6)
            return Resource.Failure(error = ValidationError.Password.TOO_SHORT)

        return Resource.Success(Unit)
    }
}