package com.perullheim.homework.presentation.screen.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.BusinessError
import com.perullheim.homework.domain.core.onFailure
import com.perullheim.homework.domain.core.onSuccess
import com.perullheim.homework.domain.usecase.find.CheckAccountUseCase
import com.perullheim.homework.domain.usecase.validator.ValidateAccountNumberUseCase
import com.perullheim.homework.domain.usecase.validator.ValidateMobileNumberUseCase
import com.perullheim.homework.domain.usecase.validator.ValidatePersonalNumberUseCase
import com.perullheim.homework.presentation.model.PaymentOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindAccountViewModel @Inject constructor(
    private val checkAccount: CheckAccountUseCase,
    private val validateAccountNumber: ValidateAccountNumberUseCase,
    private val validatePersonalNumber: ValidatePersonalNumberUseCase,
    private val validateMobileNumber: ValidateMobileNumberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FindAccountUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<FindAccountUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: FindAccountUiEvent) {
        when (event) {
            is FindAccountUiEvent.OnFind -> find(info = event.text)
            is FindAccountUiEvent.OnPaymentOptionChanged -> updateState { copy(selectedOption = event.newOption) }
        }
    }

    private fun find(info: String) {
        if (isEnteredInfoValid(enteredInfo = info).not())
            return

        viewModelScope.launch {
            checkAccount(info)
                .onSuccess { exists ->
                    if (exists) sendEffect(FindAccountUiEffect.NavigateBack)
                    else sendEffect(FindAccountUiEffect.Failure(error = BusinessError.Account.NOT_FOUND))
                }
                .onFailure {
                    sendEffect(FindAccountUiEffect.Failure(error = it))
                }
        }
    }

    private fun isEnteredInfoValid(enteredInfo: String): Boolean {
        var isValid = true

        when (_uiState.value.selectedOption) {
            PaymentOption.ACCOUNT_NUMBER -> validateAccountNumber(enteredInfo)
            PaymentOption.PERSONAL_NUMBER -> validatePersonalNumber(enteredInfo)
            PaymentOption.MOBILE_NUMBER -> validateMobileNumber(enteredInfo)
        }
            .onFailure {
                sendEffect(FindAccountUiEffect.Failure(error = it))
                isValid = false
            }

        return isValid
    }

    private fun updateState(state: FindAccountUiState.() -> FindAccountUiState) {
        _uiState.update(state)
    }

    private fun sendEffect(effect: FindAccountUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}