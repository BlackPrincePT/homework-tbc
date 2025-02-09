package com.perullheim.homework.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

private val TOKEN_KEY = stringPreferencesKey(name = "token_auth")
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class DataStoreManager(private val context: Context) {

    @OptIn(ExperimentalCoroutinesApi::class)
    val userToken: Flow<String?> = oneTimeUserToken.flatMapLatest { oneTimeToken ->
        if (oneTimeToken != null)
            flowOf(oneTimeToken)
        else
            context.dataStore.data.map {
                it[TOKEN_KEY]
            }
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteUserToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    companion object {
        var oneTimeUserToken = MutableStateFlow<String?>(null)
    }
}