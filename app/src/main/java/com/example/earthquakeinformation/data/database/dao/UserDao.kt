package com.example.earthquakeinformation.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.earthquakeinformation.data.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE name = (:name) AND password = (:password) LIMIT 1")
    suspend fun findUser(name: String, password: String): UserEntity?

    @Query("SELECT COUNT(*) FROM user_table")
    suspend fun countUsers(): Int
}