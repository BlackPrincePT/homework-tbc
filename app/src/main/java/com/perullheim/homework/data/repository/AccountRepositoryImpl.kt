package com.perullheim.homework.data.repository

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
    private val accountDtoMapper: AccountDtoMapper
) : AccountRepository {

    override suspend fun fetchAccounts(): Resource<List<Account>, DataError.Network> {
        val result = networkUtils.handleHttpRequest { accountService.fetchAccounts() }

        return result.mapList(accountDtoMapper::mapToDomain)
    }
}