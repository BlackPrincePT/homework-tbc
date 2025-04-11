package com.perullheim.homework.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.service.AuthRequest
import com.perullheim.homework.service.AuthService
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _registerResult = MutableLiveData<String>()
    val registerResult: LiveData<String> get() = _registerResult

    fun register(email: String, username: String, password: String) {
        val credentials = AuthRequest(email, password)

        viewModelScope.launch {
            _registerResult.value = try {
                val response = AuthService.instance.register(credentials)

                if (response.isSuccessful) {
                    "Registered successfully!"
                } else {
                    "Could not register: ${response.code()}"
                }
            } catch (e: Throwable) {
                "Could not register: ${e.message}"
            }
        }
    }
}