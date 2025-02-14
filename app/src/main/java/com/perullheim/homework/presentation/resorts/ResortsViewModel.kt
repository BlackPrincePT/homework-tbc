package com.perullheim.homework.presentation.resorts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.data.api.model.ApiResort
import com.perullheim.homework.domain.model.Resort
import com.perullheim.homework.domain.repositories.ResortsRepository
import com.perullheim.homework.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResortsViewModel @Inject constructor(
    private val resortsRepository: ResortsRepository
) : ViewModel() {

    private val _resorts = MutableStateFlow<List<Resort>>(emptyList())
    val resorts: StateFlow<List<Resort>>
        get() = _resorts

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String>
        get() = _errorMessage

    init {
        fetchResorts()
    }

    private fun fetchResorts() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = resortsRepository.fetchResorts()) {
                is Resource.Success -> _resorts.emit(result.data.map(ApiResort::toDomain))
                is Resource.Error -> _errorMessage.emit(result.message)
            }
        }
    }
}