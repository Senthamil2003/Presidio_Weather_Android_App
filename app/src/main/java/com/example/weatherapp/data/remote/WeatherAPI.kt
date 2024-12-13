package com.example.weatherapp.data.remote
import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast.json")
    suspend fun MakeWeatherConnection(
        @Query("key") apiKey: String = "0f688ff65044440aab965629242811",
        @Query("q") location: String,
        @Query("days") days: Int = 1,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ):WeatherResponse
}
