package com.plcoding.weatherapp.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(
    weatherDataPerDate: Map<Int, List<WeatherData>>?,
    modifier: Modifier = Modifier,
    onWeatherDataPerDateClick: (WeatherData) -> Unit
) {
    weatherDataPerDate?.get(0)?.let { list ->
        Column(
            modifier = modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Today",
                modifier = Modifier.align(Start),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(content = {
                items(list) { weatherData ->
                    HourlyWeatherData(
                        modifier = modifier
                            .wrapContentHeight()
                            .padding(10.dp)
                            .clickable(
                                onClick = { onWeatherDataPerDateClick(weatherData) }
                            ),
                        weatherData = weatherData
                    )
                }
            })
        }
    }
}

@Composable
fun HourlyWeatherData(
    weatherData: WeatherData,
    modifier: Modifier
) {
    val time = weatherData.time.format(
        DateTimeFormatter.ofPattern("HH:mm")
    )
    val color = Color.LightGray
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            color = color
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = weatherData.weatherType.weatherDesc,
            Modifier.height(30.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${weatherData.temperaturesCelcius}°C",
            color = Color.White
        )
    }
}
