package com.plcoding.weatherapp.data.repositoryImp

import android.app.Application
import android.content.pm.PackageManager
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.Task
import com.plcoding.weatherapp.domain.repository.LocationRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class LocationRepositoryImpTest {

    private lateinit var locationRepository: LocationRepository
    private lateinit var application: Application
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        application = mockk<Application>()
        fusedLocationProviderClient = mockk<FusedLocationProviderClient>()
        locationRepository = LocationRepositoryImp(
            fusedLocationProviderClient = fusedLocationProviderClient,
            application = application
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getCurrentLocation_whenGPSIsOFF_returnsNull() = runBlocking {
        // Arrange
        val locationManagerObject: LocationManager = mockk()
        coEvery { application.checkSelfPermission(any()) } returns PackageManager.PERMISSION_GRANTED
        coEvery { application.getSystemService(any()) } returns locationManagerObject
        coEvery { locationManagerObject.isProviderEnabled(any()) } returns false
        // Act
        val location = locationRepository.getCurrentLocation()
        testDispatcher.scheduler.advanceUntilIdle()
        // Assert
        assertNull(location)
    }

    @Test
    fun getCurrentLocation_whenGPSIsOn_returnsLocation() = runBlocking {
        // Arrange
        val locationManagerObject: LocationManager = mockk()
        coEvery { application.checkSelfPermission(any()) } returns PackageManager.PERMISSION_GRANTED
        coEvery { application.getSystemService(any()) } returns locationManagerObject
        coEvery { locationManagerObject.isProviderEnabled(any()) } returns true
        coEvery { fusedLocationProviderClient.lastLocation.isComplete } returns true
        coEvery { fusedLocationProviderClient.lastLocation.isSuccessful } returns true
        coEvery { fusedLocationProviderClient.lastLocation.result } returns mockLocation
        // Act
        val location = locationRepository.getCurrentLocation()
        testDispatcher.scheduler.advanceUntilIdle()
        // Assert
        assertNotNull(location)
        assertEquals(location, mockLocation)
    }

    @Test
    fun verifyFusedLocationProviderClient() = runBlocking {
        // Arrange
        val locationRequest = mockk<LocationRequest>()
        val locationCallback = mockk<LocationCallback>()
        val mockTask = mockk<Task<Void>>()
        every { fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null) } returns mockTask

        // Act
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)

        // Assert
        verify { fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null) }
    }

    companion object {
        val mockLocation = mockk<android.location.Location>()
    }
}