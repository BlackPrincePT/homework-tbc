package com.perullheim.homework.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.model.source.DataStoreManager

class ProfileViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    val currentUserToken
        get() = dataStoreManager.userToken

    suspend fun logout() {
        dataStoreManager.deleteUserToken()
        DataStoreManager.oneTimeUserToken.value = null
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val dataStoreManager = DataStoreManager(application)
                ProfileViewModel(dataStoreManager)
            }
        }
    }
}