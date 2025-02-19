package com.perullheim.homework.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.repository.AuthRepository
import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState>
        get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            authRepository.getCurrentSession().collectLatest { currentSession ->
                _state.update {
                    it.copy(loading = false, success = currentSession != null)
                }
            }
        }
    }

    fun login(email: String, password: String, shouldRemember: Boolean) {
        _state.update {
            it.copy(loading = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val errorMessage = authRepository.login(email, password, shouldRemember)

            withContext(Dispatchers.Main) {
                errorMessage?.let { message ->
                    _state.update {
                        it.copy(loading = false, failure = Event(message))
                    }
                }
            }
        }
    }
}