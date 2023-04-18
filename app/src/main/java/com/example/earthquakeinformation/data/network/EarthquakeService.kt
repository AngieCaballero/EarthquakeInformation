package com.example.earthquakeinformation.data.network

import com.example.earthquakeinformation.data.model.EarthquakeApiResponse

interface EarthquakeService {

    suspend fun getEarthquakesFromDate(startTime: String, endTime: String): EarthquakeApiResponse?
}