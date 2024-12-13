package com.example.weatherapp.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.weatherapp.data.model.AppState
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.model.WeatherResponseState
import com.example.weatherapp.viewModel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun WeatherHead(viewState: AppState,onSearch:(String )->Unit){
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),  // Increased padding for better spacing
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(!isSearchVisible){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)  // Consistent spacing between icon and text
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFF5A7BEB),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = viewState.weatherData?.location?.name ?: "nothing",
                        modifier = Modifier.padding(vertical = 16.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF5A7BEB),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                IconButton(  // Wrapped in IconButton for better touch target
                    onClick = { isSearchVisible=true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF5A7BEB),
                        modifier = Modifier.size(28.dp)
                    )
                }

            }
            else{
               OutlinedTextField(
                   modifier = Modifier.fillMaxWidth()
                       .padding(8.dp)
                       .height(50.dp)
                   ,
                   value = searchQuery,
                   onValueChange = {searchQuery=it},
                   trailingIcon = {
                       Row {
                           if(searchQuery.length==0){
                               IconButton(
                                   onClick = {
                                       isSearchVisible = false
                                       searchQuery = ""
                                   }
                               ) {
                                   Icon(
                                       Icons.Default.Close,
                                       contentDescription = "Close search",
                                       tint = Color(0xFF5A7BEB)
                                   )
                               }

                           }
                           else{
                               IconButton(
                                   onClick = {
                                       onSearch(searchQuery)
                                       isSearchVisible = false
                                       searchQuery = ""
                                   }
                               ) {
                                   Icon(
                                       Icons.Default.Search,
                                       contentDescription = "Close search",
                                       tint = Color(0xFF5A7BEB)
                                   )
                               }

                           }


                       }
                   }


               )
            }


        }

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        )
//        {
//            Icon(
//                imageVector = Icons.Default.LocationOn,
//                contentDescription = "Location",
//                tint = MaterialTheme.colorScheme.primary,
//                modifier = Modifier.size(36.dp)
//            )
//            Spacer(modifier =Modifier.width(5.dp))
//
//            OutlinedTextField(value = "", onValueChange = {},  placeholder = {
//                Text(
//                    text = "Search for Location",
//                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
//                )
//            })
//
//
//
//
//
//        }

    }

}
fun formatDateToWeekdayMonth(dateString: String): String {
    try {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse(dateString)

        // Create output format
        val dayFormatter = SimpleDateFormat("EEE", Locale.getDefault()) // Day abbreviation
        val monthDayFormatter = SimpleDateFormat("MMM d", Locale.getDefault()) // Month and day

        // Format the date
        val weekDay = dayFormatter.format(date)
        val monthDay = monthDayFormatter.format(date)

        return "$weekDay | $monthDay"
    }
    catch (e:Exception){
        println(e)
        return ""
    }

}
@Composable
fun TemperatureIndicator(viewState:AppState){
    Column (
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF22DBE7),
                        Color(0xFF3476F6)
                    )
                ),

           shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(25.dp))
            .height(250.dp),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
      Row(
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
      ){
          Column(
              verticalArrangement = Arrangement.Center,
              modifier = Modifier.fillMaxHeight()
          )
          {

              Row (
                  horizontalArrangement =Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically

              ){

                  Text(text = formatDateToWeekdayMonth(viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.date.toString()),
                      style = TextStyle(
                          fontSize = 25.sp,
                          fontWeight = FontWeight.Medium,
                          letterSpacing = 0.5.sp,
                          color = Color.White,
                          textAlign = TextAlign.Center
                      )
                      )

              }
              Row {
                  Text(
                      text =viewState.weatherData?.current?.temp_c.toString(),
                      style = TextStyle(
                          fontSize = 60.sp,
                          fontWeight = FontWeight.ExtraBold,
                          color = Color.White

                          )
                  )

                  // Degree symbol and Celsius
                  Text(
                      text = "°C",
                      style = TextStyle(
                          fontSize = 50.sp,
                          fontWeight = FontWeight.ExtraBold,
                          color = Color.White


                          ),
                      modifier = Modifier.padding(top = 6.dp)
                  )

              }

              Text(viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.condition?.text.toString(),  style = TextStyle(
                  fontSize = 19.sp,
                  fontWeight = FontWeight.Medium,
                  letterSpacing = 0.5.sp,
                  color = Color.White,
                  textAlign = TextAlign.Center
              ))




          }

          AsyncImage(
              model ="https://${viewState.weatherData?.forecast?.forecastday?.getOrNull(viewState.selectedDayIndex)?.day?.condition?.icon?.replace("64x64","128x128")}",
              contentDescription = "Sample Image",
              modifier = Modifier.size(200.dp),
              contentScale = ContentScale.Crop
          )


      }


    }

}
