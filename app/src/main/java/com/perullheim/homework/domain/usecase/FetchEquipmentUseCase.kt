package com.perullheim.homework.domain.usecase

import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.convert
import com.perullheim.homework.domain.model.Equipment
import com.perullheim.homework.domain.repository.EquipmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchEquipmentUseCase @Inject constructor(private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(queryParam: String = ""): Flow<Resource<List<Equipment>, DataError.Network>> {
        return equipmentRepository.fetchEquipment()
            .convert { list ->
                list.filter { equipment ->
                    queryParam in equipment.name
                }
            }
    }
}