package com.plcoding.weatherapp.domain.repository

import android.location.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Location?
    suspend fun requestLocationUpdate(): Location?
}