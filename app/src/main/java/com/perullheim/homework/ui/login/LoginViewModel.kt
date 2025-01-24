package com.perullheim.homework.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.model.data.DataStoreManager
import com.perullheim.homework.model.service.AuthRequest
import com.perullheim.homework.model.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    val currentUserToken
        get() = dataStoreManager.userToken

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun login(email: String, password: String, shouldRemember: Boolean) {
        val credentials = AuthRequest(email, password)

        try {
            val response = AuthService.instance.login(credentials)

            if (!response.isSuccessful) {
                _errorMessage.value = "Could not log in: ${response.code()}"
            }

            response.body()?.token?.let { token ->
                if (shouldRemember)
                    dataStoreManager.saveUserToken(token)
                else
                    DataStoreManager.oneTimeUserToken.value = token
            }
        } catch (e: Throwable) {
            _errorMessage.value = "Could not log in: ${e.message}"
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