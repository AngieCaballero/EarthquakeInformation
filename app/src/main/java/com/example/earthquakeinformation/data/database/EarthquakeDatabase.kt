package com.example.earthquakeinformation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.earthquakeinformation.data.database.dao.UserDao
import com.example.earthquakeinformation.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class EarthquakeDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
}