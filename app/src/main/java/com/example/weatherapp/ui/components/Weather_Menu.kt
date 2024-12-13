package com.example.weatherapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.AppState
import com.example.weatherapp.data.model.WeatherResponseState


@Composable
fun Menu(viewState: AppState,changeDay:(Int)->Unit) {
    var selectedButton =viewState.selectedDayIndex
    println("sample"+selectedButton.toString())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            val buttons = listOf("Today", "Tomorrow", "10 days")

            buttons.forEachIndexed { index, text ->
                Button(
                    onClick = { changeDay(index) },


                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = if (selectedButton == index) 3.dp else 1.dp,
                            color = if (selectedButton == index) Color(0xFF5A7BEB) else Color.Blue,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(45.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton == index) Color(0xFF5A7BEB) else Color.White,
                        contentColor = if (selectedButton == index) Color.White else Color.Blue
                    )
                ) {
                    Text(text)
                }
            }
        }
    }
}