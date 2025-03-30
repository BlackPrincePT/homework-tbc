package com.perullheim.homework.presentation.accounts

import com.perullheim.homework.domain.core.Error

sealed interface AccountsUiEffect {
    data class NavigateBack(val selectedAccountId: Int) : AccountsUiEffect
    data class Failure(val error: Error) : AccountsUiEffect
}