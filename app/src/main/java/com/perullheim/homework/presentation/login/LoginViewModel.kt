package com.perullheim.homework.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.domain.repositories.AuthRepository
import com.perullheim.homework.domain.repositories.UserSessionRepository
import com.perullheim.homework.utils.NetworkUtils
import com.perullheim.homework.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userSessionRepository: UserSessionRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val currentUserToken
        get() = userSessionRepository.userToken

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun login(email: String, password: String, shouldRemember: Boolean) {
        val credentials = AuthRequest(email, password)

        val result = NetworkUtils.handleHttpRequest(apiCall = { authRepository.login(credentials) })

        when (result) {
            is Resource.Success -> {
                if (shouldRemember)
                    userSessionRepository.saveUserToken(result.data.token)
                else
                    DataStoreManager.oneTimeUserToken.value = result.data.token
            }

            is Resource.Error -> _errorMessage.value = result.message
        }
    }
}