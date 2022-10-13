package com.plcoding.weatherapp.data.repositoryImp

import com.plcoding.weatherapp.data.database.WeatherDAO
import com.plcoding.weatherapp.data.entity.Weather
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import javax.inject.Inject

class WeatherDatabaseRepositoryImp @Inject constructor(private val weatherDAO: WeatherDAO) : WeatherDatabaseRepository {
    override suspend fun getAllWeatherRecord(): List<Weather> {
        return weatherDAO.getAllRecord()
    }

    override suspend fun insertWeatherData(weather: Weather) {
        weatherDAO.insetWeatherRecord(weather)
    }
}