package com.perullheim.homework.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.usecase.user.LoadMoreUsersUseCase
import com.perullheim.homework.domain.usecase.user.ObserveCachedUserStreamUseCase
import com.perullheim.homework.domain.usecase.user.RefreshUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadMoreUsers: LoadMoreUsersUseCase,
    private val refreshUsers: RefreshUsersUseCase,
    observeCachedUserStream: ObserveCachedUserStreamUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            refreshUsers()
            loadMoreUsers()
            observeCachedUserStream().collectLatest {
                updateState { copy(users = it) }
            }
        }
    }

    fun onEvent(event: HomeUiEvent) = viewModelScope.launch {
        when (event) {
            HomeUiEvent.RefreshUsers -> refreshUsers()
            HomeUiEvent.LoadMoreUsers -> loadMoreUsers().also { println("jeeee") }
        }
    }

    private fun updateState(change: HomeUiState.() -> HomeUiState) {
        uiState = uiState.change()
    }

    private fun sendEffect(effect: HomeUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}