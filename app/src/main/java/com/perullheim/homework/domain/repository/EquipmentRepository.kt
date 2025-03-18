package com.perullheim.homework.domain.repository

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.model.Equipment
import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {
    fun fetchEquipment(): Flow<Resource<List<Equipment>, DataError.Network>>
}