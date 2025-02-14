package com.perullheim.homework.domain.model

data class Resort(
    val id: Int,
    val coverUrl: String,
    val price: String,
    val title: String,
    val location: String,
    val reactionCount: Int,
    val rating: Int
)
