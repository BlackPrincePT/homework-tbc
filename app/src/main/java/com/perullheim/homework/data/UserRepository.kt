package com.perullheim.homework.data

import com.perullheim.homework.helper.toUser
import com.perullheim.homework.model.User
import com.perullheim.homework.model.UserDto
import com.perullheim.homework.service.HomeService
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao, private val homeService: HomeService) {

    fun readAllData(): Flow<List<User>> {
        return userDao.readAllData()
    }

    private fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun fetchUsersFromRemote() {
        val response = homeService.fetchUsers()

        if (response.isSuccessful && response.body() != null) {
            val remoteUsers = response.body()!!.users.map(UserDto::toUser)
            remoteUsers.forEach(this::addUser)
        }
    }
}