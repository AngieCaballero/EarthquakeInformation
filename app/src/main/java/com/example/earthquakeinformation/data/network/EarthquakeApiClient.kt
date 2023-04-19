package com.example.earthquakeinformation.data.network

import com.example.earthquakeinformation.data.model.EarthquakeApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthquakeApiClient {

    @GET("query?format=geojson")
    suspend fun getEarthquakesFromDate(
        @Query("starttime") startTime: String,
        @Query("endtime") endTime: String
    ): Response<EarthquakeApiResponse>
}