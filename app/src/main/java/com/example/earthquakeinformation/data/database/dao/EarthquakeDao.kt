package com.example.earthquakeinformation.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.earthquakeinformation.data.database.entities.EarthquakeEntity

@Dao
interface EarthquakeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarthquakeQueryHistory(earthquake: List<EarthquakeEntity>)

    @Query("SELECT * FROM earthquake_table")
    suspend fun getEarthquakeQueryHistory(): List<EarthquakeEntity>?

    @Query("SELECT * FROM earthquake_table WHERE id = (:id)")
    suspend fun getEarthquakeDetails(id: String): EarthquakeEntity?

    @Query("DELETE FROM earthquake_table")
    suspend fun clearEarthquakeTable()
}