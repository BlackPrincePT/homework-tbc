package com.perullheim.homework.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.perullheim.homework.data.cache.model.CachedUser
import com.perullheim.homework.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun fetchUsers(): Flow<List<CachedUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: CachedUser)
}