package com.perullheim.homework.data.repository

import com.perullheim.homework.data.local.room.UserDao
import com.perullheim.homework.data.local.room.model.UserEntity
import com.perullheim.homework.data.local.room.model.mapper.toDomain
import com.perullheim.homework.data.remote.model.UserDto
import com.perullheim.homework.data.remote.model.mapper.toEntity
import com.perullheim.homework.data.remote.service.UserService
import com.perullheim.homework.data.remote.util.NetworkUtils
import com.perullheim.homework.domain.core.DataError
import com.perullheim.homework.domain.core.Resource
import com.perullheim.homework.domain.core.map
import com.perullheim.homework.domain.core.onSuccess
import com.perullheim.homework.domain.core.onSuccessAsync
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao,
    private val networkUtils: NetworkUtils
) : UserRepository {

    private var currentPage = 1
    private var totalPages = 1

    override fun fetchUsersStream(): Flow<List<User>> {
        return userDao.fetchUsers()
            .map { flow -> flow.map(UserEntity::toDomain) }
    }

    override suspend fun cacheMoreUsers(): Resource<Unit, DataError.Network> {
        if (currentPage > totalPages)
            return Resource.Failure(error = DataError.Network.NOT_FOUND)

        return networkUtils.handleHttpRequest { userService.fetchUsers(currentPage) }
            .onSuccessAsync {
                userDao.cacheUsers(it.users.map(UserDto::toEntity))
                totalPages = it.totalPages
                currentPage++
            }
            .map { Unit }
    }

    override suspend fun clearCache() {
        userDao.clearUsersCache()
        currentPage = 0
    }
}