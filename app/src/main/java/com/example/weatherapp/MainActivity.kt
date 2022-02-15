package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var forecastButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastButton =findViewById(R.id.forecast_button)
        forecastButton.setOnClickListener(){
            startActivity(Intent(this, Forecast::class.java))

            }
        }


    }



