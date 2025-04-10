package com.perullheim.homework.domain.usecase.user

import com.perullheim.homework.domain.repository.UserRepository
import javax.inject.Inject

class LoadMoreUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.cacheMoreUsers()
}