package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {

    private val apiKey = "43d3f89c03787217902a3910dc8baaa0"

    private lateinit var api:weatherApi
    private lateinit var cityName: TextView
    private lateinit var currentTemp: TextView
    private lateinit var conditionIcon: ImageView
    private lateinit var feelsLikeTemp: TextView
    private lateinit var lowTemp: TextView
    private lateinit var highTemp: TextView
    private lateinit var humidity: TextView
    private lateinit var weatherPressure: TextView


    private lateinit var forecastButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityName = findViewById(R.id.city_name)
        currentTemp = findViewById(R.id.temperature)
        conditionIcon = findViewById(R.id.condition_icon)
        feelsLikeTemp = findViewById(R.id.feel_like)
        lowTemp = findViewById(R.id.low)
        highTemp = findViewById(R.id.high)
        humidity = findViewById(R.id.humidity)
        weatherPressure = findViewById(R.id.pressure_density)


        // Intent activity for forecast button when clicked
        forecastButton =findViewById(R.id.forecast_button)
        forecastButton.setOnClickListener(){
            startActivity(Intent(this, ForecastActivity::class.java))

            }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(weatherApi::class.java)

        }

    override fun onResume(){
        super.onResume()
        val callCurrentConditions: Call<CurrentConditions> = api.getCurrentConditions("55130")
        callCurrentConditions.enqueue(object : Callback<CurrentConditions>{
            override fun onResponse(
                callCurrentConditions: Call<CurrentConditions>,
                response: Response<CurrentConditions>
            ) {
               val currentConditions = response.body()
                currentConditions?.let {
                    bindCurrentConditionData(it) }
            }

            override fun onFailure(call: Call<CurrentConditions>, t: Throwable) {

            }

        })

    }

    @SuppressLint("StringFormatInvalid")
    private fun bindCurrentConditionData(currentConditions: CurrentConditions){
        cityName.text = currentConditions.name
        currentTemp.text = getString(R.string.temperature,currentConditions.main.temp.toInt())
        feelsLikeTemp.text = getString(R.string.feels_like, currentConditions.main.feelsLike.toInt())
        lowTemp.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
        highTemp.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
        humidity.text = getString(R.string.humidity, currentConditions.main.humidity.toInt())
        weatherPressure.text = getString(R.string.pressure, currentConditions.main.pressure.toInt())

        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(conditionIcon)

    }


    }



