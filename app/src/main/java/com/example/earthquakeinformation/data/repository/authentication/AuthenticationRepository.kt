package com.example.earthquakeinformation.data.repository.authentication

import com.example.earthquakeinformation.data.domain.User

interface AuthenticationRepository {

    suspend fun createUser(user: User)

    suspend fun authenticateUser(name: String, password: String): Boolean

    suspend fun countUsers(): Int
}