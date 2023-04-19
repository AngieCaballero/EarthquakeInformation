package com.example.earthquakeinformation.data.repository.earthquake

import com.example.earthquakeinformation.data.database.dao.EarthquakeDao
import com.example.earthquakeinformation.data.database.entities.toEntity
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.data.domain.toEarthquake
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
        return earthquakeDao.getEarthquakeQueryHistory()?.map { earthquakeEntity ->
            earthquakeEntity.toEarthquake()
        }
    }

    override suspend fun insertEarthquakeInDatabase(earthquakeList: List<Earthquake>) {
        earthquakeDao.clearEarthquakeTable()
        earthquakeDao.insertEarthquakeQueryHistory(earthquakeList.map { earthquake ->
            earthquake.toEntity()
        })
    }

    override suspend fun getEarthquakeDetails(id: String): Earthquake? {
        return earthquakeDao.getEarthquakeDetails(id)?.toEarthquake()
    }
}