package com.perullheim.homework.domain.usecase.find

import com.perullheim.homework.domain.repository.AccountRepository
import javax.inject.Inject

class CheckAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(info: String) = accountRepository.checkAccount(accountNumber = info)
}