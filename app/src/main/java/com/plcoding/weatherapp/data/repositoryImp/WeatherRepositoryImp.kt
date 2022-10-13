package com.plcoding.weatherapp.data.repositoryImp

import com.plcoding.weatherapp.data.mapper.toWeatherInfo
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherAPI: WeatherAPI,
) : WeatherRepository {

    override suspend fun getWeatherData(long: Double, lat: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(weatherAPI.getWeatherData(lat = lat, long = long).hourly?.toWeatherInfo())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}