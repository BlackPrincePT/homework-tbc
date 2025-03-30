package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.model.Account

interface AccountRepository {
    suspend fun fetchAccounts(): Resource<List<Account>, DataError.Network>
    suspend fun checkAccount(accountNumber: String): Resource<Boolean, DataError.Network>
}