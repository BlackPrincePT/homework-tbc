package com.perullheim.homework.helper

import com.perullheim.homework.model.User
import com.perullheim.homework.model.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        about = about,
        activationStatus = activationStatus
    )
}