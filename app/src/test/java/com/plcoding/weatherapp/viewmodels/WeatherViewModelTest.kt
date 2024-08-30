package com.plcoding.weatherapp.viewmodels

import com.plcoding.weatherapp.domain.repository.LocationRepository
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.utils.Result
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class WeatherViewModelTest {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var locationRepository: LocationRepository
    private lateinit var weatherDatabaseRepository: WeatherDatabaseRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        weatherRepository = mockk()
        locationRepository = mockk()
        weatherDatabaseRepository = mockk()
        viewModel =
            WeatherViewModel(weatherRepository, locationRepository, weatherDatabaseRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getWeatherData_whenNoLocationIsAvailable_returnsError() = runBlocking {
        // Arrange
        coEvery { locationRepository.getCurrentLocation() } returns null
        coEvery { locationRepository.requestLocationUpdate() } returns null

        // Act
        viewModel.getWeatherData()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assert(viewModel.weatherInfoState.value is Result.Error)
    }

    @Test
    fun getWeatherData_whenLocationIsAvailable_returnsSuccess() = runBlocking {
        // Arrange
        val currentLocation = mockk<android.location.Location>()
        coEvery { currentLocation.longitude } returns 10.0
        coEvery { currentLocation.latitude } returns 20.0
        coEvery { locationRepository.getCurrentLocation() } returns currentLocation

        coEvery { weatherRepository.getWeatherData(any(), any()) } returns null

        // Act
        viewModel.getWeatherData()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assert(viewModel.weatherInfoState.value is Result.Success)
    }

    @Test
    fun getWeatherData_whenLocationUpdateIsAvailable_insertToDatabaseFails() = runBlocking {
        // Arrange
        val currentLocation = mockk<android.location.Location>()
        coEvery { currentLocation.longitude } returns 10.0
        coEvery { currentLocation.latitude } returns 20.0
        coEvery { locationRepository.getCurrentLocation() } returns currentLocation

        coEvery { weatherDatabaseRepository.insertWeatherData(any()) } throws Exception()
        coEvery { weatherRepository.getWeatherData(any(), any()) } returns weatherInfo

        // Act
        viewModel.getWeatherData()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assert(viewModel.weatherInfoState.value is Result.Error)
    }

    @Test
    fun getWeatherData_whenLocationUpdateIsAvailable_insertToDatabase() = runBlocking {
        // Arrange
        val currentLocation = mockk<android.location.Location>()
        coEvery { currentLocation.longitude } returns 10.0
        coEvery { currentLocation.latitude } returns 20.0
        coEvery { locationRepository.getCurrentLocation() } returns currentLocation

        coEvery { weatherDatabaseRepository.insertWeatherData(any()) } just Runs
        coEvery { weatherRepository.getWeatherData(any(), any()) } returns weatherInfo

        // Act
        viewModel.getWeatherData()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assert(viewModel.weatherInfoState.value is Result.Success)
    }

    @Test
    fun updateWeatherData() = runBlocking {
        // Act
        viewModel.updateWeatherData(weatherData)

        // Assert
        assert(viewModel.weatherDataState.value == weatherData)
    }

    companion object {

        private val weatherData = WeatherData(
            time = LocalDateTime.now(),
            temperaturesCelcius = 20.0,
            pressure = 1013.25,
            windSpeed = 5.0,
            humidity = 60.0,
            weatherType = WeatherType.Foggy
        )

        private val weatherInfo = WeatherInfo(
            weatherDataPerDate = mapOf(),
            currentWeatherData = weatherData
        )
    }
}