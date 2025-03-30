package com.perullheim.homework.data.repository

import com.perullheim.homework.data.remote.model.AccountCheckDto
import com.perullheim.homework.data.remote.model.mapper.AccountCheckDtoMapper
import com.perullheim.homework.data.remote.model.mapper.AccountDtoMapper
import com.perullheim.homework.data.remote.service.AccountService
import com.perullheim.homework.data.remote.util.NetworkUtils
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.core.mapList
import com.perullheim.homework.domain.model.Account
import com.perullheim.homework.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
    private val networkUtils: NetworkUtils,
    private val accountDtoMapper: AccountDtoMapper,
    private val accountCheckDtoMapper: AccountCheckDtoMapper
) : AccountRepository {

    override suspend fun fetchAccounts(): Resource<List<Account>, DataError.Network> {
        return networkUtils.handleHttpRequest { accountService.fetchAccounts() }
            .mapList(accountDtoMapper::mapToDomain)
    }

    override suspend fun checkAccount(accountNumber: String): Resource<Boolean, DataError.Network> {
        val accountCheckRequest = AccountCheckDto.Request(accountNumber = accountNumber)

        return networkUtils.handleHttpRequest { accountService.checkAccount(accountCheckRequest) }
            .map(accountCheckDtoMapper::mapToDomain)
    }
}