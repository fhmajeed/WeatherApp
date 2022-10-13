package com.plcoding.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.plcoding.weatherapp.data.database.WeatherDAO
import com.plcoding.weatherapp.data.database.WeatherDatabase
import com.plcoding.weatherapp.data.remote.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            "WeatherDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun bindWeatherRepositoryImp(weatherDatabase: WeatherDatabase): WeatherDAO {
        return weatherDatabase.weatherDao()
    }




    @Singleton
    @Provides
    fun getLocationClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

}