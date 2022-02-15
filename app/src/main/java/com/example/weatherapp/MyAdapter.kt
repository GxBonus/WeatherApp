package com.example.weatherapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


// Create MyAdapter that inherits (:) RecyclerView.Adapter
// which hold's a ViewHolder type that is pass into <MyAdapter.ViewHolder
class MyAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // create ViewHolder class as a sub class of adapter class
    // to describe the item view's data and metadata
    @SuppressLint("NewApi")
    class ViewHolder(forecastView: View) : RecyclerView.ViewHolder(forecastView){
        private val dateView: TextView = forecastView.findViewById(R.id.date)
        private val tempView: TextView = forecastView.findViewById(R.id.current_temp)
        private val highTempView: TextView = forecastView.findViewById(R.id.high_temp)
        private val lowTempView: TextView = forecastView.findViewById(R.id.low_temp)
        private var sunriseView: TextView = forecastView.findViewById(R.id.sunrise_time)
        private val sunsetView: TextView = forecastView.findViewById(R.id.sunset_time)

        // use DateTimeFormatter to format the data
        private val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
        private val sunriseTimeFormatter = DateTimeFormatter.ofPattern("hh:mma")
        private val sunsetTimeFormatter = DateTimeFormatter.ofPattern("hh:mma")


        @SuppressLint("SetTextI18n", "StringFormatInvalid", "StringFormatMatches")
        fun bind(forecastData: DayForecast){
            val dateInstant = Instant.ofEpochSecond(forecastData.date)
            val sunriseInstant = Instant.ofEpochSecond(forecastData.sunrise)
            val sunsetInstant = Instant.ofEpochSecond(forecastData.sunset)
            val dateTime = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault())
            val sunriseTime = LocalDateTime.ofInstant(sunriseInstant, ZoneId.systemDefault())
            val sunsetTime = LocalDateTime.ofInstant(sunsetInstant, ZoneId.systemDefault())

            // Had to create variable and initialize it to formatter.format
            // in order to call context.getString from .text to display string to the TextViews
            val sunriseFormat = sunriseTimeFormatter.format(sunriseTime)
            val sunsetFormat = sunsetTimeFormatter.format(sunsetTime)
            val tempValue = forecastData.temp.day.toInt()
            val highTemp = forecastData.temp.maxTemperature.toInt()
            val lowTemp = forecastData.temp.minTemperature.toInt()


            dateView.text = dateFormatter.format(dateTime)
            sunriseView.text = sunriseView.context.getString(R.string.sunrise, sunriseFormat)
            sunsetView.text = sunsetView.context.getString(R.string.sunset, sunsetFormat)
            tempView.text = sunsetView.context.getString(R.string.temp, tempValue)
            highTempView.text = highTempView.context.getString(R.string.high_temp, highTemp)
            lowTempView.text = lowTempView.context.getString(R.string.low_temp, lowTemp)


        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list items
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_date, parent, false)
        return ViewHolder(view)
    }
    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace
        // the contents of the view with that element
        holder.bind(data[position])
    }

    // Return the size of the your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}

