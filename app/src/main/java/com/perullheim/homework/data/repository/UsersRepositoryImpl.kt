package com.perullheim.homework.data.repository

import com.perullheim.homework.data.api.NetworkUtils
import com.perullheim.homework.data.api.UsersService
import com.perullheim.homework.data.api.model.ApiUser
import com.perullheim.homework.data.api.model.Resource
import com.perullheim.homework.data.cache.DatabaseManager
import com.perullheim.homework.data.cache.model.CachedUser
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.model.pagination.PaginatedUsers
import com.perullheim.homework.domain.model.pagination.Pagination
import com.perullheim.homework.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: UsersService,
    private val cache: DatabaseManager,
    private val networkUtils: NetworkUtils
) : UsersRepository {

    override fun getUsers(): Flow<List<User>> {
        return cache.usersDao().fetchUsers()
            .distinctUntilChanged()
            .map { usersList ->
                usersList.map(CachedUser::toDomain)
            }
    }

    override suspend fun getMoreUsers(page: Int): Pagination {
        val result = networkUtils.handleHttpRequest(apiCall = { api.fetchUsers(page) })

        if (result is Resource.Success) {
            val data = result.data.toDomain()

            storeUsers(data.users)

            return data.pagination
        }

        return Pagination.EMPTY
    }

    private suspend fun storeUsers(users: List<User>) {
        users.forEach {
            println(it)
            cache.usersDao().addUser(user = CachedUser.fromDomain(it))
        }
    }
}