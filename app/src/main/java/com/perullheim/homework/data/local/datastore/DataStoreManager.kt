package com.perullheim.homework.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.perullheim.homework.domain.cache.CacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tbc.preferences")

class DataStoreManager(private val context: Context) : CacheManager {

    override fun <T> observe(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.data.map { it[key] }
    }

    override suspend fun <T> read(key: Preferences.Key<T>): T? {
        return context.dataStore.data.first()[key]
    }

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { it[key] = value }
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}