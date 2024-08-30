package com.plcoding.weatherapp.data.repositoryImp

import com.plcoding.weatherapp.data.remote.Hourly
import com.plcoding.weatherapp.data.remote.WeatherAPI
import com.plcoding.weatherapp.data.remote.WeatherDataDAO
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImpTest {

    private lateinit var weatherRepository: WeatherRepository
    private val weatherApi: WeatherAPI = mockk()
    private val weatherDataDAO = mockk<WeatherDataDAO>()
    private val hourly = mockk<Hourly>()

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImp(weatherApi)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getWeatherData(): Unit = runBlocking {

        // Arrange
        coEvery { weatherApi.getWeatherData(any(), any()) } returns weatherDataDAO
        coEvery { hourly.time } returns listOf()
        coEvery { weatherDataDAO.hourly } returns hourly

        //Act
        val weatherInfo = weatherRepository.getWeatherData(lat, long)

        //Assert
        assertNotNull(weatherInfo)
        coVerify {
            weatherApi.getWeatherData(lat, lat)
        }
    }

    companion object {
        const val lat = 0.0
        const val long = 0.0
    }
}