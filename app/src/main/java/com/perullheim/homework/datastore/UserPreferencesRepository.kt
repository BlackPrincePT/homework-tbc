package com.perullheim.homework.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.codelab.android.datastore.UserPreferences
import com.perullheim.homework.model.User
import kotlinx.coroutines.flow.catch

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
private const val ERROR_TAG: String = "UserPreferencesRepo"

private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer
)

class UserPreferencesRepository private constructor(private val context: Context) {

    val userPreferencesFlow = context.userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(ERROR_TAG, "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateUser(user: User) {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().apply {
                setFirstName(user.firstName)
                setLastName(user.lastName)
                setEmail(user.email)
            }.build()
        }
    }

    companion object {
        @Volatile
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: UserPreferencesRepository? = null

        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserPreferencesRepository(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }
    }
}