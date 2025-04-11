package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateEmailUseCaseTest {

    private val validateEmail = ValidateEmailUseCase()

    @Test
    fun `email is empty returns EMPTY error`() {
        val result = validateEmail(email = "")
        assertTrue(result is Resource.Failure && result.error == ValidationError.Email.EMPTY)
    }
}