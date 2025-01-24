package com.perullheim.homework.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.model.data.DataStoreManager

class HomeViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

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
                HomeViewModel(dataStoreManager)
            }
        }
    }
}