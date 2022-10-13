package com.plcoding.weatherapp.di

import com.plcoding.weatherapp.data.repositoryImp.WeatherDatabaseRepositoryImp
import com.plcoding.weatherapp.data.repositoryImp.WeatherRepositoryImp
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImp: WeatherRepositoryImp): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindWeatherDatabaseRepository(weatherDatabaseRepositoryImp: WeatherDatabaseRepositoryImp) : WeatherDatabaseRepository
}