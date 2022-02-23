package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi {
    @GET("weather")
    fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = "43d3f89c03787217902a3910dc8baaa0",
    ): Call<CurrentConditions>

    @GET("forecast/daily")
    fun getForecast(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = "43d3f89c03787217902a3910dc8baaa0",
        @Query("cnt") cnt: Int = 16
    ) : Call<Forecast>

}