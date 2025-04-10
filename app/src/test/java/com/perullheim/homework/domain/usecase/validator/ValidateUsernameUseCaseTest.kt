package com.perullheim.homework.domain.usecase.validator

import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.ValidationError
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateUsernameUseCaseTest {

    private val validateUsername = ValidateUsernameUseCase()

    @Test
    fun `username is empty returns EMPTY error`() {
        val result = validateUsername("")
        assertTrue(result is Resource.Failure && result.error == ValidationError.Username.EMPTY)
    }

    @Test
    fun `username is too short returns TOO_SHORT error`() {
        val result = validateUsername("12")
        assertTrue(result is Resource.Failure && result.error == ValidationError.Username.TOO_SHORT)
    }

    @Test
    fun `username is valid returns Success`() {
        val result = validateUsername("user123")
        assertTrue(result is Resource.Success)
    }
}