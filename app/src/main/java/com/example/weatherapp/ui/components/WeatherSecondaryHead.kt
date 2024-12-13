package com.example.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.AppState

@Composable
fun SecondaryTemperature(viewState: AppState){
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

                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(25.dp))
            .height(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            "Next 10 days Forecast"

        )

    }

}