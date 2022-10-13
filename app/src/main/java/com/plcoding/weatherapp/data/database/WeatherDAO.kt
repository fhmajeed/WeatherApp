package com.plcoding.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.weatherapp.data.entity.Weather

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM Weather")
    suspend fun getAllRecord() : List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetWeatherRecord(weather: Weather)

    @Query("DELETE FROM Weather WHERE id=:key")
    suspend fun deleteWeatherRecord(key : Int)

    @Query("DELETE FROM Weather")
    suspend fun deleteAll()
}