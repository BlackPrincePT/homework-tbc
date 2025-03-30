package com.perullheim.homework.presentation.screen.accounts

import com.perullheim.homework.domain.core.Error
import com.perullheim.homework.presentation.model.UiAccount

sealed interface AccountsUiEffect {
    data class NavigateBack(val selectedAccount: UiAccount) : AccountsUiEffect
    data class Failure(val error: Error) : AccountsUiEffect
}