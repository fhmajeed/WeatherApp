package com.plcoding.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.data.entity.Weather
import com.plcoding.weatherapp.domain.repository.LocationRepository
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) : ViewModel() {

    var weatherState by mutableStateOf(WeatherState())
        private set

    init {
        println("Init Called")
    }

    fun getWeatherData() {

        viewModelScope.launch {
            weatherState = weatherState.copy(
                weatherInfo = null, isLoading = true, error = null
            )
            val currentLocation = locationRepository.getCurrentLocation()

            if (currentLocation == null) {
                weatherState = weatherState.copy(
                    weatherInfo = null, isLoading = false, error = "Location Error"
                )

                return@launch
            }
            val data = weatherRepository.getWeatherData(
                long = currentLocation.longitude,
                lat = currentLocation.latitude
            )
            weatherState = when (data) {
                is Resource.Success -> {

                    data.data?.currentWeatherData?.let{ weatherData ->
                        weatherDatabaseRepository.insertWeatherData(Weather(
                            time = weatherData.time,
                            humidity = weatherData.humidity,
                            temperaturesCelsius = weatherData.temperaturesCelcius,
                            pressure = weatherData.pressure,
                            windSpeed = weatherData.windSpeed
                        ))
                    }

                    weatherState.copy(
                        weatherInfo = data.data, isLoading = false, error = null
                    )
                }
                is Resource.Error -> {

                    val weatherInfo = weatherDatabaseRepository.getAllWeatherRecord()[0]

                    weatherState.copy(
                        weatherInfo = null, isLoading = false, error = data.message
                    )
                }
            }
        }
    }
}