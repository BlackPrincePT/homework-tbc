package com.perullheim.homework.domain.usecase

import com.perullheim.homework.domain.repository.AccountRepository
import javax.inject.Inject

class FetchAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke() = accountRepository.fetchAccounts()
}