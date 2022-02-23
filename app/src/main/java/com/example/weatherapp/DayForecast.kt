package com.example.weatherapp

import com.squareup.moshi.Json

// data class primary constructor need to have at least one parameter
data class DayForecast(
    @Json(name = "dt") val date:Long,
    @Json(name = "sunrise") val sunrise:Long,
    @Json (name = "sunset") val sunset:Long,
    @Json(name = "temp") val temp:ForecastTemp,
    val pressure:Float,
    val humidity:Int
    )



