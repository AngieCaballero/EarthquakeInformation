package com.example.earthquakeinformation.data.repository.earthquake

import com.example.earthquakeinformation.data.domain.Earthquake

interface EarthquakeRepository {

    suspend fun getEarthquakeListFromDate(startTime: String, endTime: String): List<Earthquake>?
    suspend fun getEarthquakeFromQueryHistory(): List<Earthquake>?
    suspend fun insertEarthquakeInDatabase(earthquake: List<Earthquake>)
    suspend fun getEarthquakeDetails(id: String): Earthquake?
}