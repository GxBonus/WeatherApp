package com.example.weatherapp

import com.squareup.moshi.Json

data class Forecast(
    val forecastDaily:List<DayForecast>,
)
