package com.perullheim.homework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.perullheim.homework.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseManager::class.java,
                    "main_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}