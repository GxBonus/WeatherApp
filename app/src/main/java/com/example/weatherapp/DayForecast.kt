package com.example.weatherapp

// data class primary constructor need to have at least one parameter
data class DayForecast(
    val date:Long,
    val sunrise:Long,
    val sunset:Long,
    val temp:ForecastTemp,
    val pressure:Float,
    val humidity:Int)



