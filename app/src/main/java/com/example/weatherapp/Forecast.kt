package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


open class Forecast : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val adapterData = listOf<DayForecast>(
        DayForecast(1620540000,1620540000L,1620590100,ForecastTemp(9F, 65F,80F), 950F, 70),// May 9
        DayForecast(1620635400,1620630000, 1620678900,ForecastTemp(10F, 50F, 70F), 1030F, 55),// May 10
        DayForecast(1620723600,1620719160, 1620767460,ForecastTemp(11F, 60F, 85F), 850F, 45),// May 11
        DayForecast(1620813600,1620802440,1620852420, ForecastTemp(12F, 55F,75F), 900F, 30),// May 12
        DayForecast(1620905400,1620884700,1620939960,ForecastTemp(13F, 45F, 60F), 1005F, 25),// May 13
        DayForecast(1620997200,1620968100,1621029300,ForecastTemp(14F, 30F,65F), 760F, 60),// May 14
        DayForecast(1621089000,1621062660,1621110300,ForecastTemp(15F, 45F, 90F), 830F, 75),// May 15
        DayForecast(1621183020,1621147500,1621202340,ForecastTemp(16F,30F, 86F), 790F, 40),// May 16
        DayForecast(1621267200,1621235100,1621281900,ForecastTemp(17F, 25F, 55F), 600F, 35),// May 17
        DayForecast(1621355400,1621317300,1621373940,ForecastTemp(18F, 40F, 92F), 650F, 80),// May 18
        DayForecast(1621443600,1621404120,1621457700,ForecastTemp(19F,25F,79F), 920F, 20),// May 19
        DayForecast(1621531800,1621494600,1621545600,ForecastTemp(20F,35F,80F), 900F, 70),// May 20
        DayForecast(1621620000,1621578900,1621630140,ForecastTemp(21F,55F, 85F), 830F, 25),// May 21
        DayForecast(1621708200,1621667160,1621716900,ForecastTemp(22F, 55F, 90F), 1010F, 10),// May 22
        DayForecast(1621796400,1621753140,1621804500,ForecastTemp(23F,30F,75F), 670F, 90),// May 23
        DayForecast(1621890000,1621835100,1621893900,ForecastTemp(24F, 45F,80F), 800F, 85) // May 24
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // create recyclerView variable and initialize it to find the view of the recycler view ID
        // Remember to set layoutManager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter(adapterData)
    }
}