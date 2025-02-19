package com.perullheim.homework.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.perullheim.homework.data.api.NetworkUtils
import com.perullheim.homework.data.api.UsersService
import com.perullheim.homework.data.api.model.Resource
import com.perullheim.homework.data.cache.DatabaseManager
import com.perullheim.homework.data.cache.model.CachedUser

@OptIn(ExperimentalPagingApi::class)
class HomeRemoteMediator(
    private val api: UsersService,
    private val cache: DatabaseManager,
    private val networkUtils: NetworkUtils
) : RemoteMediator<Int, CachedUser>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CachedUser>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val totalItems = cache.usersDao().getUsersCount()
                (totalItems / state.config.pageSize) + 1
            }
        }

        try {
            val response = networkUtils.handleHttpRequest { api.fetchUsers(page) }
            if (response !is Resource.Success) {
                return MediatorResult.Error(Exception("Error fetching data"))
            }

            val usersData = response.data.toDomain()
            val endOfPaginationReached = usersData.users.isEmpty()

            cache.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    cache.usersDao().clearUsersCache()
                }

                usersData.users.forEach { user ->
                    cache.usersDao().cacheUser(CachedUser.fromDomain(user))
                }
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}