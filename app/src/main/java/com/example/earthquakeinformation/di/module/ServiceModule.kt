package com.example.earthquakeinformation.di.module

import com.example.earthquakeinformation.data.network.EarthquakeService
import com.example.earthquakeinformation.data.network.EarthquakeServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun bindEarthquakeService(
        earthquakeService: EarthquakeServiceImpl
    ): EarthquakeService
}