package com.perullheim.homework.model

import java.util.UUID

data class GameSlot(
    val id: UUID = UUID.randomUUID(),
    val coordinates: Pair<Int, Int>,
    var value: GameSlotValue? = null
)