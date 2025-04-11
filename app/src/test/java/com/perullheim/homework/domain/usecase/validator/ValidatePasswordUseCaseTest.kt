package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private val validatePassword = ValidatePasswordUseCase()

    @Test
    fun `password is empty returns EMPTY error`() {
        val result = validatePassword(password = "")
        assertTrue(result is Resource.Failure && result.error == ValidationError.Password.EMPTY)
    }

    @Test
    fun `password is too short returns TOO_SHORT error`() {
        val result = validatePassword(password ="123")
        assertTrue(result is Resource.Failure && result.error == ValidationError.Password.TOO_SHORT)
    }

    @Test
    fun `password is valid returns Success`() {
        val result = validatePassword(password ="123456")
        assertTrue(result is Resource.Success)
    }
}