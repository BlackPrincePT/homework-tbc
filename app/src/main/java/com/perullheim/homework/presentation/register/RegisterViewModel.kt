package com.perullheim.homework.presentation.register

import androidx.lifecycle.ViewModel
import com.perullheim.homework.data.api.model.AuthRequest
import com.perullheim.homework.data.api.AuthService
import com.perullheim.homework.utils.NetworkUtils
import com.perullheim.homework.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun register(email: String, password: String): Boolean {
        val credentials = AuthRequest(email, password)

        val result =
            NetworkUtils.handleHttpRequest(apiCall = { AuthService.instance.register(credentials) })

        return when (result) {
            is Resource.Success -> true
            is Resource.Error -> {
                _errorMessage.value = result.message
                false
            }
        }
    }
}