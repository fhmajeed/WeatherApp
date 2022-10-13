package com.plcoding.weatherapp.di

import com.plcoding.weatherapp.data.repositoryImp.LocationRepositoryImp
import com.plcoding.weatherapp.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationServices(locationRepositoryImp: LocationRepositoryImp) : LocationRepository
}