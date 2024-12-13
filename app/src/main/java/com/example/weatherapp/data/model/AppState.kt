package com.example.weatherapp.data.model

data class AppState(
    val weatherData: WeatherResponse? = null,
    val loading: Boolean = true,
    val error: String? = null,
    val selectedDayIndex: Int = 0,
    val searchLocation:String?=null,
    val noOfDays:Int=1,
)
