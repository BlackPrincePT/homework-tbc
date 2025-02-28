package com.perullheim.homework.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.data.repository.abstraction.AddressRepository
import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.maps.model.Address
import com.perullheim.homework.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MapsViewState.INITIAL)
    val state = _state.asStateFlow()

    init {
        fetchAddresses()
    }

    private fun fetchAddresses() = viewModelScope.launch(Dispatchers.IO) {
        setLoading()

        _state.update { oldState ->
            when (val result = addressRepository.fetchAddresses()) {
                is Resource.Success -> oldState.copy(
                    isLoading = false,
                    addresses = Event(result.data.map(Address::fromDto)),
                    errorMsg = null
                )

                is Resource.Error -> oldState.copy(
                    isLoading = false,
                    errorMsg = Event(result.message)
                )
            }
        }

    }

    private fun setLoading(isLoading: Boolean = true) {
        _state.update { oldState ->
            oldState.copy(isLoading = isLoading)
        }
    }
}