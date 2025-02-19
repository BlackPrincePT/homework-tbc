package com.perullheim.homework.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.repository.AuthRepository
import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState>
        get() = _state

    fun register(email: String, password: String) {
        _state.update {
            it.copy(loading = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val errorMessage = authRepository.register(email, password)

            withContext(Dispatchers.Main) {
                _state.update {
                    if (errorMessage != null)
                        it.copy(loading = false, success = false, failure = Event(errorMessage))
                    else
                        it.copy(loading = false, success = true)
                }
            }
        }
    }
}