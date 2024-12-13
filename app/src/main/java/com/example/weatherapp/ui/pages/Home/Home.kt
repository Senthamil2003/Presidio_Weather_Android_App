package com.example.weatherapp.ui.pages.Home

import RequestLocationPermissions
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.components.HourlyForcast
import com.example.weatherapp.ui.components.Menu
import com.example.weatherapp.ui.components.SecondaryTemperature
import com.example.weatherapp.ui.components.TemperatureIndicator
import com.example.weatherapp.ui.components.WeatherHead
import com.example.weatherapp.ui.components.WeatherInfo
import com.example.weatherapp.viewModel.WeatherViewModel

@Composable
fun HomeComponent(modifier: Modifier){
    val viewModel= hiltViewModel <WeatherViewModel>()
    val viewState by viewModel.state
    println("helloindex"+viewState.selectedDayIndex.toString())
    println("helllo"+viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.date)
    RequestLocationPermissions(
        onPermissionsGranted = {
            viewModel.fetchLocationDetails()
        },
        onPermissionsDenied = {
            print("Errror")
        }
    )
    when{

        viewState.loading->{
            Column(modifier = Modifier.padding(25.dp).fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }


        }

        viewState.error!=null->{

            Text(viewState.error.toString())
        }

        else->{

            viewState.weatherData?.let {
                Column(modifier = modifier
                    .verticalScroll(rememberScrollState()) ) {

                    WeatherHead(viewState=viewState,onSearch = { query ->
                        viewModel.searchLocation(query)
                    })
                    if(viewModel.state.value.selectedDayIndex<=1){
                        TemperatureIndicator(viewState=viewState)

                    }
                    else{
                        SecondaryTemperature(viewState=viewState)
                    }

                    Menu(viewState=viewState,changeDay={
                            day->viewModel.ChangeDays(day)
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                    if(viewModel.state.value.selectedDayIndex<=1){
                        WeatherInfo(viewState=viewState)
                        HourlyForcast(viewState=viewState)

                    }



                }
            }

        }



    }

}
@Composable
fun ErrorPage(){
    Text("Permission Denied")
}