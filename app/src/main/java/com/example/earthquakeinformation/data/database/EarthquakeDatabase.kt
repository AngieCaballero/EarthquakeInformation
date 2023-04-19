package com.example.earthquakeinformation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.earthquakeinformation.data.database.dao.EarthquakeDao
import com.example.earthquakeinformation.data.database.dao.UserDao
import com.example.earthquakeinformation.data.database.entities.EarthquakeEntity
import com.example.earthquakeinformation.data.database.entities.UserEntity

@Database(entities = [
    UserEntity::class,
    EarthquakeEntity::class
 ], version = 1, exportSchema = false)
abstract class EarthquakeDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getEarthquakeDao(): EarthquakeDao
}