package com.perullheim.homework.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.perullheim.homework.domain.model.User

@Entity(tableName = "users_table")
data class CachedUser(
    @PrimaryKey
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {
    fun toDomain(): User {
        return User(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }

    companion object {
        fun fromDomain(user: User): CachedUser {
            return CachedUser(
                id = user.id,
                email = user.email,
                firstName = user.firstName,
                lastName = user.lastName,
                avatar = user.avatar
            )
        }
    }
}