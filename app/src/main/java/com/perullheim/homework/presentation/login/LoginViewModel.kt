package com.perullheim.homework.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.utils.NetworkUtils
import com.perullheim.homework.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    val currentUserToken
        get() = dataStoreManager.userToken

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun login(email: String, password: String, shouldRemember: Boolean) {
        val credentials = AuthRequest(email, password)

        val result =
            NetworkUtils.handleHttpRequest(apiCall = { AuthService.instance.login(credentials) })

        when (result) {
            is Resource.Success -> {
                if (shouldRemember)
                    dataStoreManager.saveUserToken(result.data.token)
                else
                    DataStoreManager.oneTimeUserToken.value = result.data.token
            }
            is Resource.Error -> _errorMessage.value = result.message
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val dataStoreManager = DataStoreManager(application)
                LoginViewModel(dataStoreManager)
            }
        }
    }
}