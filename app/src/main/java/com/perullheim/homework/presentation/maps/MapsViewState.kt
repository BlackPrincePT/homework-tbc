package com.perullheim.homework.presentation.maps

import com.perullheim.homework.presentation.Event
import com.perullheim.homework.presentation.maps.model.Address

data class MapsViewState(
    val isLoading: Boolean,
    val addresses: Event<List<Address>>,
    val errorMsg: Event<String>?
) {
    companion object {
        val INITIAL = MapsViewState(
            isLoading = false,
            addresses = Event(emptyList()),
            errorMsg = null
        )
    }
}