package com.perullheim.homework.ui.register

import androidx.lifecycle.ViewModel
import com.perullheim.homework.model.service.AuthRequest
import com.perullheim.homework.model.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.Delegates

class RegisterViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    suspend fun register(email: String, password: String): Boolean {
        val credentials = AuthRequest(email, password)

        try {
            val response = AuthService.instance.register(credentials)

            if (response.isSuccessful) {
                return true
            } else {
                _errorMessage.value = "Could not register: ${response.code()}"
            }
        } catch (e: Throwable) {
            _errorMessage.value = "Could not register: ${e.message}"
        }

        return false
    }
}