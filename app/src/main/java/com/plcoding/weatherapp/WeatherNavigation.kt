package com.plcoding.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.plcoding.weatherapp.compose.WeatherDetailScreen
import com.plcoding.weatherapp.compose.WeatherLandingScreen
import com.plcoding.weatherapp.viewmodels.WeatherViewModel
import kotlinx.serialization.Serializable

@Composable
fun WeatherNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LandingScreenRoute) {

        composable<LandingScreenRoute> {

            val parentEntry = remember(it) {
                navController.getBackStackEntry(LandingScreenRoute)
            }
            val viewModel = hiltViewModel<WeatherViewModel>(parentEntry)

            WeatherLandingScreen(
                onNavigateToDetailScreen = {
                    navController.navigate(DetailScreenRoute)
                },
                viewModel = viewModel
            )
        }

        composable<DetailScreenRoute> {
            val parentEntry = remember(it){
                navController.getBackStackEntry(LandingScreenRoute)
            }

            val viewModel = hiltViewModel<WeatherViewModel>(parentEntry)
            val weatherData by viewModel.weatherData.collectAsState()

            WeatherDetailScreen(weatherData = weatherData)
        }
    }
}

@Serializable
data object LandingScreenRoute

@Serializable
data object DetailScreenRoute