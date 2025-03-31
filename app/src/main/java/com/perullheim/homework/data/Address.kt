package com.perullheim.homework.data

import java.util.UUID

data class Address(
    val id: UUID = UUID.randomUUID(),
    var label: String,
    var city: String,
    var street: String
)