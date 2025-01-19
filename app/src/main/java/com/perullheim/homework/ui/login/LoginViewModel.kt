package com.perullheim.homework.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.service.AuthRequest
import com.perullheim.homework.service.AuthService
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> get() = _loginResult

    fun login(email: String, password: String) {
        val credentials = AuthRequest(email, password)

        viewModelScope.launch {
            _loginResult.value = try {
                val response = AuthService.instance.login(credentials)

                if (response.isSuccessful) {
                    "Logged in successfully!"
                } else {
                    "Could not log in: ${response.code()}"
                }
            } catch (e: Throwable) {
                "Could not log in: ${e.message}"
            }
        }
    }
}