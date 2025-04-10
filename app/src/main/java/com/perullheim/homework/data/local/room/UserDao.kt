package com.perullheim.homework.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.perullheim.homework.data.local.room.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table")
    fun fetchUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheUsers(users: List<UserEntity>)

    @Query("DELETE FROM users_table")
    suspend fun clearUsersCache()
}