package com.perullheim.homework.data.repository

import com.perullheim.homework.data.remote.core.NetworkUtils
import com.perullheim.homework.data.remote.model.EquipmentDto
import com.perullheim.homework.data.remote.model.mappers.toDomainList
import com.perullheim.homework.data.remote.service.EquipmentService
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.convert
import com.perullheim.homework.domain.model.Equipment
import com.perullheim.homework.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentService: EquipmentService,
    private val networkUtils: NetworkUtils
) : EquipmentRepository {

    override fun fetchEquipment(): Flow<Resource<List<Equipment>, DataError.Network>> {
        val equipmentDtoFlow = networkUtils.handleHttpRequest { equipmentService.fetchEquipment() }

        return equipmentDtoFlow.convert { list ->
            list.flatMap(EquipmentDto::toDomainList)
        }
    }
}