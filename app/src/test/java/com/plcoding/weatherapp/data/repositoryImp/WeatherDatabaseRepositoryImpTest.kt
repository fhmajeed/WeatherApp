package com.plcoding.weatherapp.data.repositoryImp

import com.plcoding.weatherapp.data.database.WeatherDAO
import com.plcoding.weatherapp.data.entity.Weather
import com.plcoding.weatherapp.domain.repository.WeatherDatabaseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class WeatherDatabaseRepositoryImpTest {

    private lateinit var weatherDatabaseRepository: WeatherDatabaseRepository
    private var weatherDAO: WeatherDAO = mockk()
    private var weather = mockk<Weather>()

    @Before
    fun setUp() {
        weatherDatabaseRepository = WeatherDatabaseRepositoryImp(weatherDAO)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getAllWeatherRecord_verifyWeatherDAO_AllRecord() = runBlocking {
        coEvery { weatherDAO.getAllRecord() } returns listOf()
        weatherDatabaseRepository.getAllWeatherRecord()
        coVerify { weatherDAO.getAllRecord() }
    }

    @Test
    fun insertWeatherData_verifyWeatherDAO_InsertWeatherRecord() = runBlocking {
        coEvery { weatherDAO.insetWeatherRecord(any()) } returns Unit
        weatherDatabaseRepository.insertWeatherData(weather)
        coVerify { weatherDAO.insetWeatherRecord(any()) }
    }
}