package com.perullheim.homework.data.repository

import com.perullheim.homework.data.remote.model.LoginRequest
import com.perullheim.homework.data.remote.service.LoginService
import com.perullheim.homework.data.remote.util.NetworkUtils
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.model.Session
import com.perullheim.homework.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val networkUtils: NetworkUtils
) : LoginRepository {

    override suspend fun login(email: String, password: String): Resource<Session, DataError.Network> {
        val loginRequest = LoginRequest(email, password)

        return networkUtils.handleHttpRequest { loginService.login(loginRequest) }
            .map { Session(token = it.token, email = email) }
    }
}