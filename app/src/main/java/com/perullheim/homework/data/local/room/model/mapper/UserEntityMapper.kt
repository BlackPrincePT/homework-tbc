package com.perullheim.homework.data.local.room.model.mapper

import com.perullheim.homework.data.local.room.model.UserEntity
import com.perullheim.homework.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}