package com.perullheim.homework.domain.usecase.user

import com.perullheim.homework.domain.repository.UserRepository
import javax.inject.Inject

class ObserveCachedUserStreamUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke() = userRepository.fetchUsersStream()
}