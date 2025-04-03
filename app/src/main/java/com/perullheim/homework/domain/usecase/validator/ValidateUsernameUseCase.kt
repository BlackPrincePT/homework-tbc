package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

class ValidateUsernameUseCase @Inject constructor() {
    operator fun invoke(username: String): Resource<Unit, ValidationError.Username> {
        if (username.isEmpty())
            return Resource.Failure(error = ValidationError.Username.EMPTY)

        if (username.length < 3)
            return Resource.Failure(error = ValidationError.Username.TOO_SHORT)

        return Resource.Success(Unit)
    }
}