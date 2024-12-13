package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.weatherapp.data.model.WeatherResponseState

@Composable
fun WeatherInfo(viewState: AppState){
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.White)  // Add background color to see shadow
                    .clip(RoundedCornerShape(10.dp))
                    .height(100.dp),  // Removed duplicate height modifier
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center// Center content vertically
            ) {
                AsyncImage(
                    model = "https://cdn-icons-png.flaticon.com/128/16338/16338728.png",
                    contentDescription = "wind Image",
                    modifier = Modifier.size(50.dp)
                    ,
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Wind Speed",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF5A7BEB),
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.maxwind_kph} km/hr",
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )

                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.White)  // Add background color to see shadow
                    .clip(RoundedCornerShape(10.dp))
                    .height(100.dp),  // Removed duplicate height modifier
                verticalAlignment = Alignment.CenterVertically , // Center content vertically
                        horizontalArrangement = Arrangement.Center// Center content vertically
            ) {
                AsyncImage(
                    model = "https://icons.veryicon.com/png/o/business/icons-for-internet-of-things-projects/temperature-and-humidity-4.png",
                    contentDescription = "Humidity Image",
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Humidity",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF5A7BEB),
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.avghumidity} %",

                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )

                }
            }

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.White)  // Add background color to see shadow
                    .clip(RoundedCornerShape(10.dp))
                    .height(100.dp),  // Removed duplicate height modifier
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center// Center content vertically
            ) {
                AsyncImage(
                    model = "https://cdn-icons-png.flaticon.com/256/5132/5132998.png",
                    contentDescription = "Rain Image",
                    modifier = Modifier.size(50.dp)
                    ,
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Rain Chance",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF5A7BEB),
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.totalprecip_mm} %",

                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )

                }
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.White)  // Add background color to see shadow
                    .clip(RoundedCornerShape(10.dp))
                    .height(100.dp),  // Removed duplicate height modifier
                verticalAlignment = Alignment.CenterVertically , // Center content vertically
                horizontalArrangement = Arrangement.Center// Center content vertically
            ) {
                AsyncImage(
                    model = "https://img.freepik.com/premium-vector/air-pressure-icon-vector-image-can-be-used-weather_120816-215405.jpg?w=360",
                    contentDescription = "wind Image",
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Air Pressure",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF5A7BEB),
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.uv} in",
                        modifier = Modifier.padding(horizontal = 16.dp)  // Add some padding to text
                    )

                }
            }

        }
    }
}