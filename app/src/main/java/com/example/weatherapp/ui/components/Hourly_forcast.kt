package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.data.model.AppState
import com.example.weatherapp.data.model.Hour
import com.example.weatherapp.data.model.WeatherResponseState

fun convertTimeTo12Hour(time: String): String {
    val hour = time.substringBefore(":").toInt()
    val minute = time.substringAfter(":")

    return when (hour) {
        0 -> "12$minute AM"
        12 -> "12$minute PM"
        in 1..11 -> "$hour AM"
        else -> "${hour - 12} PM"
    }
}
@Composable
fun HourlyForcast(viewState: AppState){
    val hourlyData: List<Hour> = viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.hour ?: emptyList()


    Column(
        modifier = Modifier.padding(8.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp)
            .fillMaxWidth(),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)
        ) {
//            AsyncImage(
//                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6bqqBcwDWTQLCAP88Zk7rnJWXPLEXUilT0A&s",
//                contentDescription = "Clock Image",
//                modifier = Modifier.size(35.dp),
//                contentScale = ContentScale.Crop
//            )'
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Search",
                tint = Color(0xFF5A7BEB),
                modifier = Modifier.size(28.dp)
            )
            Text("Hourly Forcast",
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp,
                color = Color(0xFF5A7BEB),
            ),)


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            hourlyData.forEach { hourly ->
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(Color.White)
                        .height(108.dp)
                        .width(90.dp),


                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(convertTimeTo12Hour(hourly.time.drop(11).dropLast(2)))
                    AsyncImage(
                        model = "https://${hourly.condition.icon}",
                        contentDescription = hourly.condition.text,
                        modifier = Modifier.size(50.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(hourly.temp_c.toString())
                }
            }
        }



    }
}

