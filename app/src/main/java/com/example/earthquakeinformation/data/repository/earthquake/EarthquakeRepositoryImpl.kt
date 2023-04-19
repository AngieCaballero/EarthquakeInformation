package com.example.earthquakeinformation.data.repository.earthquake

import android.util.Log
import com.example.earthquakeinformation.data.database.dao.EarthquakeDao
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.data.domain.toEarthquakeList
import com.example.earthquakeinformation.data.network.EarthquakeService
import javax.inject.Inject

class EarthquakeRepositoryImpl @Inject constructor(
    private val earthquakeDao: EarthquakeDao,
    private val earthquakeService: EarthquakeService
): EarthquakeRepository {

    override suspend fun getEarthquakeListFromDate(
        startTime: String,
        endTime: String
    ): List<Earthquake>? {
        return earthquakeService.getEarthquakesFromDate(startTime, endTime)?.toEarthquakeList()
    }

    override suspend fun getEarthquakeFromQueryHistory(): List<Earthquake>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertEarthquakeInDatabase(earthquake: Earthquake) {
        TODO("Not yet implemented")
    }

    override suspend fun getEarthquakeDetails(id: String): Earthquake? {
        TODO("Not yet implemented")
    }
}