package com.plcoding.weatherapp.presentation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var checkLocationPermission: ActivityResultLauncher<Array<String>>
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLocationPermission =
            registerForActivityResult(RequestMultiplePermissions()) { perm ->
                if (perm[ACCESS_FINE_LOCATION] == true || perm[ACCESS_COARSE_LOCATION] == true) {
                    viewModel.getWeatherData()
                }
            }

        checkLocationPermission.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))

        setContent {
            WeatherAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        WeatherCard(
                            weatherState = viewModel.weatherState,
                            backgroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        WeatherForecast(viewModel.weatherState)
                    }

                    if (viewModel.weatherState.isLoading == true) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.weatherState.error?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}