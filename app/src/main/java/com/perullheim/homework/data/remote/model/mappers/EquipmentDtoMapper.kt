package com.perullheim.homework.data.remote.model.mappers

import com.perullheim.homework.data.remote.model.EquipmentDto
import com.perullheim.homework.domain.model.Equipment

fun EquipmentDto.toDomainList(depth: Int = 0): List<Equipment> {
    val current = Equipment(
        id = id,
        name = name,
        nameDe = nameDe,
        createdAt = createdAt,
        parentAmount = depth
    )

    val childrenEquipment = children.flatMap { it.toDomainList(depth = depth + 1) }

    return listOf(current) + childrenEquipment
}
