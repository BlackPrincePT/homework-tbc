package com.perullheim.homework.data.cache.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.perullheim.homework.data.cache.model.CachedUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CachedUser>

    @Query("SELECT * FROM users_table")
    fun fetchUsers(): Flow<List<CachedUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheUser(user: CachedUser)

    @Query("DELETE FROM users_table")
    suspend fun clearUsersCache()

    @Query("SELECT COUNT(*) FROM users_table")
    suspend fun getUsersCount(): Int
}