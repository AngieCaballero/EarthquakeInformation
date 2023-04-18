package com.example.earthquakeinformation.data.repository.authentication

import com.example.earthquakeinformation.data.database.dao.UserDao
import com.example.earthquakeinformation.data.database.entities.toEntity
import com.example.earthquakeinformation.data.domain.User
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): AuthenticationRepository {

    override suspend fun createUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun authenticateUser(name: String, password: String): Boolean {
        return userDao.findUser(name, password) != null
    }

    override suspend fun countUsers(): Int {
        return userDao.countUsers()
    }
}