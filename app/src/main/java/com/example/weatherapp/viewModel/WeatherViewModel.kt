package com.example.weatherapp.viewModel
import LocationPermissionHandler
import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.AppState
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.redux.Action
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.reduxkotlin.Store
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor
    (
    private val store: Store<AppState>,
    private val repository: WeatherRepository,
          val  application: Application

            ):ViewModel(){

    private val _state = mutableStateOf(store.state)
    val state: State<AppState> = _state
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)


    init {
        viewModelScope.launch {

            store.subscribe {
                _state.value = store.state
            }
        }
        fetchLocationDetails()
//        fetchCategory()
    }


    private fun fetchCategory(){
        viewModelScope.launch {
            try {
                store.dispatch(Action.FetchWeather)
                val response= repository.GetWeatherData(state.value.searchLocation.toString(),state.value.selectedDayIndex)
                store.dispatch(Action.WeatherFetchSuccess(response))

            }
            catch (e:Exception){
                store.dispatch(Action.WeatherFetchError(e.message ?: "Unknown error"))
            }
        }
    }

    fun searchLocation(query: String) {
        store.dispatch(Action.Search(query))
        fetchCategory()
    }
    fun ChangeDays(days:Int){

      store.dispatch(Action.SelectDay(days))
        fetchCategory()
    }


    @SuppressLint("MissingPermission")
    fun fetchLocationDetails() {
        val permissionHandler = LocationPermissionHandler(application)

        if (!permissionHandler.hasLocationPermissions()) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    viewModelScope.launch {
                        val geocoder = Geocoder(application, Locale.getDefault())
                        try {
                            val addresses = geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            ) ?: emptyList()

                            if (addresses.isNotEmpty()) {
                                val address = addresses[0]
                                val cityName = address.locality ?: ""

                                if (cityName.isNotEmpty()) {
                                    // Dispatch city name for weather search
                                    store.dispatch(Action.Search(cityName))
                                    // Fetch weather data for the location
                                    fetchCategory()
                                }
                            } else {
                                // No address found, use coordinates

                            }
                        } catch (e: Exception) {
                            store.dispatch(Action.WeatherFetchError("Error getting address: ${e.message}"))
                        }
                    }
                } ?: run {
                    store.dispatch(Action.WeatherFetchError("Unable to get location. Please ensure location services are enabled."))
                }
            }
            .addOnFailureListener { exception ->
                store.dispatch(Action.WeatherFetchError("Failed to fetch location: ${exception.message}"))
            }
            .addOnCanceledListener {
                store.dispatch(Action.WeatherFetchError("Location request was canceled"))
            }
    }

}