package com.perullheim.homework.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConstants {
    val TOKEN_KEY = stringPreferencesKey(name = "token_auth")
    val EMAIL_KEY = stringPreferencesKey(name = "email")
}