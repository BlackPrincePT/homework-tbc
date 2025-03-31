package com.perullheim.homework.model

import java.time.LocalDateTime
import java.util.UUID

data class Message(
    val id: UUID = UUID.randomUUID(),
    val body: String,
    val timestamp: LocalDateTime,
    val sender: Sender
)
