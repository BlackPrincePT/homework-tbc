package com.perullheim.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val PASSCODE_LENGTH = 4
private val PASSCODE = arrayOf(0, 9, 3, 4)

class LockViewModel : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String>
        get() = _message

    private val passcode = Array<Int?>(size = PASSCODE_LENGTH, init = { null })

    private val _passcodeFlow = MutableStateFlow(passcode)
    val passcodeFlow: StateFlow<Array<Int?>>
        get() = _passcodeFlow

    private val nonNullCount: Int
        get() = passcode.count { it != null }


    fun addDigit(number: Int) {
        if (nonNullCount == passcode.size - 1) {
            passcode[nonNullCount] = number
            checkPasscode()
            return
        }

        passcode[nonNullCount] = number
        updateFlow()
    }

    fun removeLastDigit() {
        if (nonNullCount == 0) return
        passcode[nonNullCount - 1] = null
        updateFlow()
    }

    private fun checkPasscode() {
        viewModelScope.launch(Dispatchers.Main) {
            _message.emit(if (passcode.contentEquals(PASSCODE)) "Correct Password!" else "Incorrect Password!")

            repeat(nonNullCount) {
                removeLastDigit()
            }
        }
    }

    private fun updateFlow() {
        _passcodeFlow.value = passcode.copyOf()
    }
}