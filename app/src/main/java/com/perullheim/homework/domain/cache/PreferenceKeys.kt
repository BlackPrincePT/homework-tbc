package com.perullheim.homework.domain.cache

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val TOKEN_KEY = stringPreferencesKey(name = "token_auth")
    val EMAIL_KEY = stringPreferencesKey(name = "email")
}