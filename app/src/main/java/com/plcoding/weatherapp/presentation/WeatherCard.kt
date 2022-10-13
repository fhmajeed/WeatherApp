package com.plcoding.weatherapp.presentation

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    weatherState: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    weatherState.weatherInfo?.currentWeatherData.let { data ->
        Card(
            backgroundColor = backgroundColor,
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (data != null) {
                    Text(
                        text = "Today : ${
                            data.time.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }",
                        modifier = Modifier.align(Alignment.End),
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )
                    Image(
                        modifier = Modifier.width(200.dp),
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "${data.temperaturesCelcius.toInt()}°C",
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = data.weatherType.weatherDesc,
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround

                    ) {
                        WeatherDataDisplay(
                            vector = painterResource(id = com.plcoding.weatherapp.R.drawable.ic_drop),
                            unit = "HDP",
                            value = data.humidity.toString()
                        )
                        WeatherDataDisplay(
                            vector = painterResource(id = com.plcoding.weatherapp.R.drawable.ic_pressure),
                            unit = "ksa",
                            value = data.pressure.toString()
                        )
                        WeatherDataDisplay(
                            vector = painterResource(id = com.plcoding.weatherapp.R.drawable.ic_wind),
                            unit = "m/hr",
                            value = data.windSpeed.toString()
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )
                }
            }

           /* Text(
                text = "Today",
                modifier = Modifier.align(Alignment.Start),
                color = Color.White,
                fontSize = 20.sp
            )*/

        }
    }
}

@Composable
fun WeatherDataDisplay(vector: Painter, value: String, unit: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = vector,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = Color.White
        )
        Text(text = "$value $unit", color = Color.White)
    }
}

@Preview
@Composable
fun PreviewWeatherCard() {
    val weatherState = WeatherState(
        WeatherInfo(
            mapOf(), WeatherData(
                LocalDateTime.now(),
                50.0,
                1.1,
                100.0,
                1.5,
                WeatherType.ClearSky
            )
        ), false, null
    )
    WeatherCard(weatherState = weatherState, backgroundColor = DarkBlue)
}