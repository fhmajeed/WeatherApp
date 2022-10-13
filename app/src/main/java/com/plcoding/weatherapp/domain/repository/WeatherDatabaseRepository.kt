package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.data.entity.Weather

interface WeatherDatabaseRepository {
    suspend fun getAllWeatherRecord() : List<Weather>
    suspend fun insertWeatherData(weather: Weather)
}