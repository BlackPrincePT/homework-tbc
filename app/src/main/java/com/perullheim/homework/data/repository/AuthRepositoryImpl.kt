package com.perullheim.homework.data.repository

import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.datastore.DataStoreConstants
import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.data.api.NetworkUtils
import com.perullheim.homework.data.api.model.Resource
import com.perullheim.homework.domain.model.Session
import com.perullheim.homework.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val dataStoreManager: DataStoreManager,
    private val networkUtils: NetworkUtils
) : AuthRepository {

    private val cachedToken = dataStoreManager.readData(DataStoreConstants.TOKEN_KEY)
    private val cachedEmail = dataStoreManager.readData(DataStoreConstants.EMAIL_KEY)

    private val oneTimeUserSession = MutableStateFlow<Session?>(null)

    override fun getCurrentSession(): Flow<Session?> {
        return combine(
            cachedToken,
            cachedEmail,
            oneTimeUserSession
        ) { token, email, oneTimeSession ->
            if (!token.isNullOrBlank() && !email.isNullOrBlank()) {
                Session(token, email)
            } else {
                oneTimeSession
            }
        }
    }

    override suspend fun clearUserSession() {
        with(dataStoreManager) {
            deleteData(DataStoreConstants.TOKEN_KEY)
            deleteData(DataStoreConstants.EMAIL_KEY)
        }
    }

    override suspend fun login(email: String, password: String, shouldRemember: Boolean): String? {
        val authRequest = AuthRequest(email, password)

        val result = networkUtils.handleHttpRequest(apiCall = { authService.login(authRequest) })

        return when (result) {
            is Resource.Success -> {
                if (shouldRemember) {
                    with(dataStoreManager) {
                        saveData(DataStoreConstants.TOKEN_KEY, result.data.token)
                        saveData(DataStoreConstants.EMAIL_KEY, email)
                    }
                } else {
                    val session = Session(result.data.token, email)
                    oneTimeUserSession.emit(session)
                }
                null
            }

            is Resource.Error -> result.message
        }
    }

    override suspend fun register(email: String, password: String): String? {
        val authRequest = AuthRequest(email, password)

        val result = networkUtils.handleHttpRequest(apiCall = { authService.register(authRequest) })

        return when (result) {
            is Resource.Success -> null
            is Resource.Error -> result.message
        }
    }
}