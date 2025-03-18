package com.perullheim.homework.presentation.equipment.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.usecase.FetchEquipmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val fetchEquipment: FetchEquipmentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EquipmentUiState())
    val state = _state.asStateFlow()

    init {
        subscribeToStateUpdates()
    }

    fun onEvent(event: EquipmentUiEvent) {
        when (event) {
            is EquipmentUiEvent.OnSearchValueChanged -> _state.update { it.copy(searchFieldValue = event.newValue) }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun subscribeToStateUpdates() = viewModelScope.launch {
        _state
            .map { it.searchFieldValue }
            .distinctUntilChanged()
            .debounce(timeoutMillis = 300)
            .flatMapLatest { searchValue ->
                fetchEquipment(searchValue)
            }
            .collect { result ->
                when (result) {
                    is Resource.Success -> _state.update {
                        it.copy(
                            equipmentList = result.data,
                            isLoading = false
                        )
                    }
                    is Resource.Failure -> _state.update { it.copy(isLoading = false) }
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                }
            }
    }
}