package com.perullheim.homework.presentation.equipment.screen

import com.perullheim.homework.domain.model.Equipment

data class EquipmentUiState(
    val equipmentList: List<Equipment> = emptyList(),
    val isLoading: Boolean = false,

    // Component Values
    val searchFieldValue: String = ""
)