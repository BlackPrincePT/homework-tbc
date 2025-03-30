package com.perullheim.homework.data.remote.service

import com.perullheim.homework.BuildConfig
import com.perullheim.homework.data.remote.model.AccountCheckDto
import com.perullheim.homework.data.remote.model.AccountDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountService {
    @GET(BuildConfig.ACCOUNTS_ENDPOINT)
    suspend fun fetchAccounts(): Response<List<AccountDto>>

    @POST(BuildConfig.CHECK_ACCOUNT_ENDPOINT)
    suspend fun checkAccount(@Body accountRequest: AccountCheckDto.Request): Response<AccountCheckDto>
}