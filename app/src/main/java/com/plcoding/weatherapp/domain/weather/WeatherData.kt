package com.plcoding.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData (
    val time: LocalDateTime,
    val temperaturesCelcius : Double,
    val humidity: Double,
    val windSpeed: Double,
    val pressure: Double,
    val weatherType: WeatherType
)