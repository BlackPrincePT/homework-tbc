package com.perullheim.homework.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.perullheim.homework.data.cache.daos.UsersDao
import com.perullheim.homework.data.cache.model.CachedUser
import com.perullheim.homework.domain.model.User

@Database(entities = [CachedUser::class], version = 1, exportSchema = false)
abstract class DatabaseManager: RoomDatabase() {

    abstract fun usersDao(): UsersDao
}