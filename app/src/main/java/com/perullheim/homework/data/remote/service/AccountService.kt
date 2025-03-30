package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET

interface AccountService {
    @GET(BuildConfig.ACCOUNTS_ENDPOINT)
    suspend fun fetchAccounts(): Response<List<AccountDto>>
}