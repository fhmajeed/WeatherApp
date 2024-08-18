package com.plcoding.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.data.entity.Weather
import com.plcoding.weatherapp.domain.repository.LocationRepository
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Result<WeatherInfo>>(Result.Loading)
    val weatherState = _weatherState.asStateFlow()

    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData = _weatherData.asStateFlow()

    fun getWeatherData() = viewModelScope.launch {
        val currentLocation = locationRepository.getCurrentLocation()?: locationRepository.requestLocationUpdate()

        if (currentLocation == null) {
            _weatherState.value = Result.Error(Exception("Location Error"))
            return@launch
        }

        val data = weatherRepository.getWeatherData(
            long = currentLocation.longitude,
            lat = currentLocation.latitude
        )

        try {
            data?.currentWeatherData?.let { weatherData ->
                weatherDatabaseRepository.insertWeatherData(
                    Weather(
                        time = weatherData.time,
                        humidity = weatherData.humidity,
                        temperaturesCelsius = weatherData.temperaturesCelcius,
                        pressure = weatherData.pressure,
                        windSpeed = weatherData.windSpeed
                    )
                )
            }
            _weatherState.value = Result.Success(data = data)
        } catch (e: Exception) {
            weatherDatabaseRepository.getAllWeatherRecord()[0]
            _weatherState.value = Result.Error(e)
        }
    }

    fun updateWeatherData(weatherData: WeatherData) {
        _weatherData.value = weatherData
    }
}