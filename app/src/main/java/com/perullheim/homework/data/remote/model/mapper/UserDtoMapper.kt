package com.perullheim.homework.data.remote.model.mapper

import com.perullheim.homework.data.local.room.model.UserEntity
import com.perullheim.homework.data.remote.model.UserDto

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}