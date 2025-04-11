package com.perullheim.homework.domain.usecase.register

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit, DataError.Network> {
        return registerRepository.register(email, password)
            .map { Unit }
    }
}