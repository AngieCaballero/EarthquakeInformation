package com.example.earthquakeinformation.data.network

import com.example.earthquakeinformation.data.model.EarthquakeApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class EarthquakeServiceImpl @Inject constructor(
    private val api: EarthquakeApiClient
): EarthquakeService {

    override suspend fun getEarthquakesFromDate(
        startTime: String,
        endTime: String
    ): EarthquakeApiResponse? {
        return withContext(Dispatchers.IO) {
            val response : Response<EarthquakeApiResponse> =
                api.getEarthquakesFromDate(startTime, endTime)
            if(response.isSuccessful) response.body() else null
        }
    }
}