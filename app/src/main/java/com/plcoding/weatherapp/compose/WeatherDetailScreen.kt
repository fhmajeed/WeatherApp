package com.plcoding.weatherapp.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.weatherapp.domain.weather.WeatherData

@Composable
fun WeatherDetailScreen(
    modifier: Modifier = Modifier,
    weatherData: WeatherData?
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Humidity: ${weatherData?.humidity}"
        )
    }
}

@Preview
@Composable
private fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(weatherData = null)
}