package com.perullheim.homework.presentation.equipment.screen

sealed interface EquipmentUiEvent {
    data class OnSearchValueChanged(val newValue: String) : EquipmentUiEvent
}