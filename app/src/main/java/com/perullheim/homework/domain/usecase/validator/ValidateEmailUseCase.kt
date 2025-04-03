package com.perullheim.homework.domain.usecase.validator

import android.util.Patterns
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    operator fun invoke(email: String): Resource<Unit, ValidationError.Email> {
        if (email.isEmpty())
            return Resource.Failure(error = ValidationError.Email.EMPTY)

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not())
            return Resource.Failure(error = ValidationError.Email.INVALID)

        return Resource.Success(Unit)
    }
}