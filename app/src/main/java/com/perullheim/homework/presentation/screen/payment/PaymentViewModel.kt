package com.perullheim.homework.presentation.screen.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.BusinessError
import com.perullheim.homework.presentation.model.UiAccount
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
class PaymentViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<PaymentUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: PaymentUiEvent) {
        when (event) {
            is PaymentUiEvent.OnAccountSelected -> updateState { copy(myAccount = event.account) }
            is PaymentUiEvent.OnAccountFound -> updateState { copy(accountToPay = UiAccount.DEFAULT) }
            is PaymentUiEvent.OnAmountMyCurrencyChanged -> convertCurrency(amountString = event.value, fromMyCurrency = true)
            is PaymentUiEvent.OnAmountTheirCurrencyChanged -> convertCurrency(amountString = event.value, fromMyCurrency = false)
            PaymentUiEvent.OnContinueClick -> checkBalance()
            PaymentUiEvent.OnFromAccountClick -> sendEffect(PaymentUiEffect.NavigateToMyAccounts)
            PaymentUiEvent.OnToAccountClick -> sendEffect(PaymentUiEffect.NavigateToFindAccount)
            PaymentUiEvent.OnNavigateBack -> { }
        }
    }

    private fun checkBalance() {
        _uiState.value.myAccount?.let { myAccount ->
            if (myAccount.balance < _uiState.value.amountMyCurrency)
                sendEffect(PaymentUiEffect.Failure(error = BusinessError.Account.NOT_ENOUGH_BALANCE))
        } ?: sendEffect(PaymentUiEffect.Failure(error = BusinessError.Account.NOT_FOUND))
    }

    private fun convertCurrency(amountString: String, fromMyCurrency: Boolean) {
        if (!amountString.all { it.isDigit() } || amountString.isEmpty())
            return

        val value = amountString.toInt()
        val exchangedAmount = if (fromMyCurrency) value * 2 else value / 2

        if (_uiState.value.myAccount?.currency != _uiState.value.accountToPay?.currency) {
            updateState {
                copy(
                    amountMyCurrency = if (fromMyCurrency) value else exchangedAmount,
                    amountTheirCurrency = if (fromMyCurrency) exchangedAmount else value
                )
            }
        } else {
            updateState { copy(amountMyCurrency = value) }
        }
    }

    private fun updateState(state: PaymentUiState.() -> PaymentUiState) {
        _uiState.update(state)
    }

    private fun sendEffect(effect: PaymentUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}