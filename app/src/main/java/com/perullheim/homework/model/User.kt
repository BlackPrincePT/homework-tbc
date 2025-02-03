package com.perullheim.homework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val avatar: String?,
    val firstName: String,
    val lastName: String,
    val about: String,
    val activationStatus: Double
) {
    val fullName: String
        get() = "$firstName $lastName"

    val activationStatusMsg: String
        get() = when {
            activationStatus <= 0.0 -> "Account not activated"
            activationStatus == 1.0 -> "Online"
            activationStatus == 2.0 -> "Active a few minutes ago"
            activationStatus > 2.0 && activationStatus < 23.0 -> "Active a few hours ago"
            else -> "Active a long time ago"
        }
}