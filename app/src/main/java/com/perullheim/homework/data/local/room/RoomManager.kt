package com.perullheim.homework.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.perullheim.homework.data.local.room.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class RoomManager : RoomDatabase() {

    abstract fun userDao(): UserDao
}