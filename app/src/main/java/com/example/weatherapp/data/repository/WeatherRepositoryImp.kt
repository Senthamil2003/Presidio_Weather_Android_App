package com.example.weatherapp.data.repository
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImp(
    private val api:WeatherAPI
):WeatherRepository {
    override suspend fun GetWeatherData(location:String,noOfDays:Int): WeatherResponse {
        return api.MakeWeatherConnection(location=location, days = noOfDays+1)
    }
}