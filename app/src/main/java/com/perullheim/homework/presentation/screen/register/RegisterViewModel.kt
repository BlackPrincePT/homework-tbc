package com.perullheim.homework.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.onFailure
import com.perullheim.homework.domain.core.onSuccess
import com.perullheim.homework.domain.core.withError
import com.perullheim.homework.domain.usecase.RegisterUseCase
import com.perullheim.homework.domain.usecase.validator.ValidateEmailUseCase
import com.perullheim.homework.domain.usecase.validator.ValidatePasswordUseCase
import com.perullheim.homework.domain.usecase.validator.ValidateUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase
) : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    private val _uiEffect = MutableSharedFlow<RegisterUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            is RegisterUiEvent.OnEmailChange -> updateState { copy(email = event.value) }
            is RegisterUiEvent.OnPasswordChange -> updateState { copy(password = event.value) }
            is RegisterUiEvent.OnUsernameChange -> updateState { copy(username = event.value) }
            RegisterUiEvent.OnRegisterClick -> onRegisterClick()
        }
    }

    private fun onRegisterClick() = viewModelScope.launch {
        if (areFieldsValid().not())
            return@launch

        with(uiState) {
            register(email, password)
                .onSuccess { sendEffect(RegisterUiEffect.NavigateToLogin(email, password)) }
                .onFailure { sendEffect(RegisterUiEffect.ShowError(error = it)) }
        }
    }

    private fun areFieldsValid(): Boolean {
        var isValid = true

        validateEmail(uiState.email)
            .withError { error ->
                updateState { copy(emailError = error) }
                isValid = error == null
            }

        validateUsername(uiState.username)
            .withError { error ->
                updateState { copy(usernameError = error) }
                isValid = error == null
            }

        validatePassword(uiState.password)
            .withError { error ->
                updateState { copy(passwordError = error) }
                isValid = error == null
            }

        return isValid
    }

    private fun updateState(change: RegisterUiState.() -> RegisterUiState) {
        uiState = uiState.change()
    }

    private fun sendEffect(effect: RegisterUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}