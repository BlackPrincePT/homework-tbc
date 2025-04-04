package com.perullheim.homework.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.onFailure
import com.perullheim.homework.domain.core.onSuccess
import com.perullheim.homework.domain.core.withError
import com.perullheim.homework.domain.usecase.LoginUseCase
import com.perullheim.homework.domain.usecase.validator.ValidateEmailUseCase
import com.perullheim.homework.domain.usecase.validator.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    private val _uiEffect = MutableSharedFlow<LoginUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChange -> updateState { copy(email = event.value) }
            is LoginUiEvent.OnPasswordChange -> updateState { copy(password = event.value) }
            is LoginUiEvent.OnRememberChange -> updateState { copy(remember = event.value) }
            LoginUiEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoginClick() = viewModelScope.launch {
        if (areFieldsValid().not())
            return@launch

        with(uiState) {
            login(email, password, remember)
                .onSuccess { sendEffect(LoginUiEffect.NavigateToHome) }
                .onFailure { sendEffect(LoginUiEffect.ShowError(error = it)) }
        }
    }

    private fun areFieldsValid(): Boolean {
        var isValid = true

        validateEmail(uiState.email)
            .withError { error ->
                updateState { copy(emailError = error) }
                isValid = error == null
            }

        validatePassword(uiState.password)
            .withError { error ->
                updateState { copy(passwordError = error) }
                isValid = error == null
            }

        return isValid
    }

    private fun updateState(change: LoginUiState.() -> LoginUiState) {
        uiState = uiState.change()
    }

    private fun sendEffect(effect: LoginUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}