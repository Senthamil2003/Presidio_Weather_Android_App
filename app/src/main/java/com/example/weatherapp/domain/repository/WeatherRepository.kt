package com.example.weatherapp.domain.repository
import com.example.weatherapp.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun GetWeatherData(location:String,noOfDays:Int): WeatherResponse
}