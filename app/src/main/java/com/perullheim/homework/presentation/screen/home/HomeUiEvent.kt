package com.perullheim.homework.presentation.screen.home

sealed interface HomeUiEvent {
    data object RefreshUsers: HomeUiEvent
    data object LoadMoreUsers: HomeUiEvent
}