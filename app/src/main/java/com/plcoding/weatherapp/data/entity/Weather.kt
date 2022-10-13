package com.plcoding.weatherapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Weather")
data class Weather(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "time") val time: LocalDateTime,
    @ColumnInfo(name = "temperaturesCelsius") val temperaturesCelsius : Double,
    @ColumnInfo(name = "humidity") val humidity: Double,
    @ColumnInfo(name = "windSpeed") val windSpeed: Double,
    @ColumnInfo(name = "pressure") val pressure: Double
)
