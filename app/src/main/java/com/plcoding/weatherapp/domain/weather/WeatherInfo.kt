package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDate : Map<Int,List<WeatherData>>,
    val currentWeatherData : WeatherData?
)