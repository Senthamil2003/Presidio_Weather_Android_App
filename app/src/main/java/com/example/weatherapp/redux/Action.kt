package com.example.weatherapp.redux

import com.example.weatherapp.data.model.WeatherResponse

sealed class Action {
    object FetchWeather : Action()
    data class WeatherFetchSuccess(val weather: WeatherResponse) : Action()
    data class WeatherFetchError(val error: String) : Action()
    data class SelectDay(val index: Int) : Action()
    data class Search(val search:String):Action()


}