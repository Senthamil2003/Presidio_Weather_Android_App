package com.example.weatherapp.data.model

data class WeatherResponseState(
    val loading: Boolean = true,
    val response: WeatherResponse? = null,
    val error: String? = null
)