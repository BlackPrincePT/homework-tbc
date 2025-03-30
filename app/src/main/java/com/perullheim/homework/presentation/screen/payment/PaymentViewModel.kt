package com.perullheim.homework.presentation.screen.payment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
            is PaymentUiEvent.OnAccountSelected -> { }
            PaymentUiEvent.OnNavigateBack -> { }
        }
    }
}