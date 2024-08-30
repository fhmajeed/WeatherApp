package com.plcoding.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.weatherapp.data.entity.TimeConverter
import com.plcoding.weatherapp.data.entity.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(value = [TimeConverter::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDAO
}
