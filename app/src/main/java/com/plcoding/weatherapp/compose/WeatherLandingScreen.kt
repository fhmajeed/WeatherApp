package com.plcoding.weatherapp.compose

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.weatherapp.compose.component.WeatherCard
import com.plcoding.weatherapp.compose.component.WeatherForecast
import com.plcoding.weatherapp.compose.theme.DarkBlue
import com.plcoding.weatherapp.compose.theme.DeepBlue
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.utils.Result
import com.plcoding.weatherapp.viewmodels.WeatherViewModel


@Composable
fun hasPermission(permission: String): Boolean {
    val context = LocalContext.current
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

@Composable
fun WeatherLandingScreen(
    onNavigateToDetailScreen: (WeatherData) -> Unit,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val launcher = rememberLauncherForActivityResult(
        contract = RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            viewModel.getWeatherData()
        }
    }

    val hasLocationPermission = hasPermission(ACCESS_FINE_LOCATION)

    LaunchedEffect(key1 = Unit) {
        if (hasLocationPermission) {
            viewModel.getWeatherData()
        } else {
            launcher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
        }
    }

    val weatherState = viewModel.weatherState.collectAsState(initial = Result.Loading).value

    Box(modifier = Modifier.fillMaxSize()) {

        when (weatherState) {
            Result.Loading -> {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Result.Error -> {
                Text(
                    text = weatherState.exception.message.orEmpty(),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Result.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    WeatherCard(
                        weatherData = weatherState.data?.currentWeatherData,
                        backgroundColor = DeepBlue
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    WeatherForecast(
                        weatherDataPerDate = weatherState.data?.weatherDataPerDate,
                        onWeatherDataPerDateClick = { weatherData ->
                            viewModel.updateWeatherData(weatherData)
                            onNavigateToDetailScreen(weatherData)
                        }
                    )
                }
            }
        }
    }
}