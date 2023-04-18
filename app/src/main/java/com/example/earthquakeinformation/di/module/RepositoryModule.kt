package com.example.earthquakeinformation.di.module

import com.example.earthquakeinformation.data.repository.authentication.AuthenticationRepository
import com.example.earthquakeinformation.data.repository.authentication.AuthenticationRepositoryImpl
import com.example.earthquakeinformation.data.repository.earthquake.EarthquakeRepository
import com.example.earthquakeinformation.data.repository.earthquake.EarthquakeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthenticationRepository(
        authenticationRepository: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun bindEarthquakeRepository(
        earthquakeRepository: EarthquakeRepositoryImpl
    ): EarthquakeRepository
}