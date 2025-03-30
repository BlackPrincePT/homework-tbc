package com.perullheim.homework.presentation.screen.find

import com.perullheim.homework.domain.core.Error

sealed interface FindAccountUiEffect {
    data class Failure(val error: Error): FindAccountUiEffect
    data object NavigateBack: FindAccountUiEffect
}