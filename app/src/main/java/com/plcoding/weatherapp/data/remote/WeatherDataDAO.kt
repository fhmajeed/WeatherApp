package com.plcoding.weatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherDataDAO(
    @field:SerializedName("hourly") val hourly: Hourly? = null,
)

data class Hourly(
    @field:SerializedName("pressure_msl") val pressureMsl: List<Double>,
    @field:SerializedName("temperature_2m") val temperature2m: List<Double>,
    @field:SerializedName("relativehumidity_2m") val relativehumidity2m: List<Double>,
    @field:SerializedName("weathercode") val weathercode: List<Int>,
    @field:SerializedName("windspeed_10m") val windspeed10m: List<Double>,
    @field:SerializedName("time") val time: List<String>
)
