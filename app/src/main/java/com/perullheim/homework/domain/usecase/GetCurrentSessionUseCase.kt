package com.perullheim.homework.domain.usecase

import com.perullheim.homework.domain.repository.SessionRepository
import javax.inject.Inject

class GetCurrentSessionUseCase @Inject constructor(private val sessionRepository: SessionRepository) {
    operator fun invoke() = sessionRepository.getSession()
}