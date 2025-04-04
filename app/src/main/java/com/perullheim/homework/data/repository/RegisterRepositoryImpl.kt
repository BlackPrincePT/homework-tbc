package com.perullheim.homework.data.repository

import com.perullheim.homework.data.remote.model.RegisterRequest
import com.perullheim.homework.data.remote.service.RegisterService
import com.perullheim.homework.data.remote.util.NetworkUtils
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.model.Session
import com.perullheim.homework.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerService: RegisterService,
    private val networkUtils: NetworkUtils
) : RegisterRepository {

    override suspend fun register(email: String, password: String): Resource<Session, DataError.Network> {
        val registerRequest = RegisterRequest(email, password)

        return networkUtils.handleHttpRequest { registerService.register(registerRequest) }
            .map { Session(token = it.token, email = email) }
    }
}