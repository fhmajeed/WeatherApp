package com.plcoding.weatherapp.data.mapper

import com.plcoding.weatherapp.data.remote.Hourly
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class WeatherDataMapperHolder(
    val index: Int,
    val weatherData: WeatherData
)

fun Hourly.mapData(): Map<Int,List<WeatherData>> {

    val list = time.mapIndexed { index, time ->
        val temp = temperature2m[index]
        val humidity = relativehumidity2m[index]
        val press = pressureMsl[index]
        val wind = windspeed10m[index]
        val code = weathercode[index]

        WeatherDataMapperHolder(
            index = index,
            weatherData = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperaturesCelcius = temp,
                pressure = press,
                windSpeed = wind,
                weatherType = WeatherType.fromWMO(code),
                humidity = humidity
            )
        )
    }.groupBy {
        it.index/24
    }.mapValues {
        it.value.map { holder ->
            holder.weatherData
        }
    }

    return list
}

fun Hourly.toWeatherInfo(): WeatherInfo {

    val weatherMapper = mapData()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherMapper[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        weatherDataPerDate = weatherMapper
    )

}