package com.perullheim.homework.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val TOKEN_KEY = stringPreferencesKey(name = "token_auth")

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    val userToken: Flow<String?> = oneTimeUserToken.flatMapLatest { oneTimeToken ->
        if (oneTimeToken != null)
            flowOf(oneTimeToken)
        else
            dataStore.data.map {
                it[TOKEN_KEY]
            }
    }

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteUserToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    companion object {
        var oneTimeUserToken = MutableStateFlow<String?>(null)
    }
}