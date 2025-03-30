package com.perullheim.homework.presentation.screen.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.onFailure
import com.perullheim.homework.domain.core.onSuccess
import com.perullheim.homework.domain.usecase.accounts.FetchAccountsUseCase
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
class AccountsViewModel @Inject constructor(
    private val fetchAccounts: FetchAccountsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountsUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<AccountsUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        handleFetchedAccounts()
    }

    fun onEvent(event: AccountsUiEvent) {
        when (event) {
            is AccountsUiEvent.OnAccountSelected -> sendEffect(AccountsUiEffect.NavigateBack(event.id))
        }
    }

    private fun handleFetchedAccounts() = viewModelScope.launch {
        fetchAccounts()
            .onSuccess { updateState { copy(accounts = it) } }
            .onFailure { sendEffect(AccountsUiEffect.Failure(error = it)) }
    }

    private fun updateState(state: AccountsUiState.() -> AccountsUiState) {
        _uiState.update(state)
    }

    private fun sendEffect(effect: AccountsUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}