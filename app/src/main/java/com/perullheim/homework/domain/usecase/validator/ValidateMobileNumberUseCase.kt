package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import javax.inject.Inject

class ValidateMobileNumberUseCase @Inject constructor() {

    operator fun invoke(mobileNumber: String): Resource<Unit, ValidationError.MobileNumber> {
        if (mobileNumber.length != 9)
            return Resource.Failure(error = ValidationError.MobileNumber.INVALID_SIZE)

        if (mobileNumber.all { it.isDigit() }.not())
            return Resource.Failure(error = ValidationError.MobileNumber.INVALID_FORMAT)

        return Resource.Success(Unit)
    }
}