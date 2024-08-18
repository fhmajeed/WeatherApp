package com.plcoding.weatherapp.data.repositoryImp

import com.plcoding.weatherapp.data.mapper.toWeatherInfo
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherAPI: WeatherAPI,
) : WeatherRepository {

    @Throws(Exception::class)
    override suspend fun getWeatherData(long: Double, lat: Double): WeatherInfo? {
       return weatherAPI.getWeatherData(lat = lat, long = long).hourly?.toWeatherInfo()
    }
}