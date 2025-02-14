package com.perullheim.homework.data.repositories

import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.domain.repositories.UserSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSessionRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : UserSessionRepository {

    override val userToken: Flow<String?> = dataStoreManager.userToken

    override suspend fun saveUserToken(token: String) {
        dataStoreManager.saveUserToken(token)
    }

    override suspend fun deleteUserToken() {
        dataStoreManager.deleteUserToken()
    }
}