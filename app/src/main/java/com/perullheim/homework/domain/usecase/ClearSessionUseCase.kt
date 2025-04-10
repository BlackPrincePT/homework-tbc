package com.perullheim.homework.domain.usecase

import com.perullheim.homework.domain.cache.CacheManager
import com.perullheim.homework.domain.repository.SessionRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val cacheManager: CacheManager
) {
    suspend operator fun invoke() {
        cacheManager.clear()
        sessionRepository.clearSession()
    }
}