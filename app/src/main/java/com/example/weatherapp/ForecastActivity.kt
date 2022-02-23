package com.example.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


 class ForecastActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val adapterData = mutableListOf<DayForecast>()



    private val apiKey = "43d3f89c03787217902a3910dc8baaa0"
    private lateinit var api: weatherApi

    private lateinit var date: TextView
    private lateinit var sunrise: TextView
    private lateinit var sunset: TextView
    private lateinit var day: TextView
    private lateinit var minTemp: TextView
    private lateinit var maxTemp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        date = findViewById(R.id.date)
        sunrise = findViewById(R.id.sunrise_time)
        sunset = findViewById(R.id.sunset_time)
        day = findViewById(R.id.current_temp)
        minTemp = findViewById(R.id.low_temp)
        maxTemp = findViewById(R.id.high_temp)

        // create recyclerView variable and initialize it to find the view of the recycler view ID
        // Remember to set layoutManager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter(adapterData)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(weatherApi::class.java)
    }

    override fun onResume() {
        super.onResume()
        val callForecast: Call<Forecast> = api.getForecast("55130")
        callForecast.enqueue(object : Callback<Forecast> {
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>,
            ) {
            val dailyForecast = response.body()
                dailyForecast?.let {
                    bindDailyForecast(it)
                }

            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {

            }

        })
    }
     @SuppressLint("NewApi", "StringFormatMatches")
     private fun bindDailyForecast(dailyForecast:Forecast){

         val dateFormat = DateTimeFormatter.ofPattern("MMM dd")
         val sunriseTimeFormat = DateTimeFormatter.ofPattern("hh:mma")
         val sunsetTimeFormat = DateTimeFormatter.ofPattern("hh:mma")

         val dateInstant = Instant.ofEpochSecond(dailyForecast.forecastDaily.first().date)
         val sunriseInstant = Instant.ofEpochSecond(dailyForecast.forecastDaily.first().sunrise)
         val sunsetInstant = Instant.ofEpochSecond(dailyForecast.forecastDaily.first().sunset)
         val dateTime = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault())
         val sunriseTime = LocalDateTime.ofInstant(sunriseInstant, ZoneId.systemDefault())
         val sunsetTime = LocalDateTime.ofInstant(sunsetInstant, ZoneId.systemDefault())


         date.text = dateFormat.format(dateTime)
         sunrise.text = sunriseTimeFormat.format(sunriseTime)
         sunset.text = sunsetTimeFormat.format(sunsetTime)
         day.text = getString(R.string.temp, dailyForecast.forecastDaily.firstOrNull()?.temp?.day?.toInt())
         maxTemp.text = getString(R.string.high_temp, dailyForecast.forecastDaily.firstOrNull()?.temp?.max?.toInt())
         minTemp.text = getString(R.string.low_temp, dailyForecast.forecastDaily.firstOrNull()?.temp?.min?.toInt())

     }

}


