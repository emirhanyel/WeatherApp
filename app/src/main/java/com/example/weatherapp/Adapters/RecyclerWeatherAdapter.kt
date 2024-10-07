package com.example.weatherapp.Adapters

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.DataPackage.HoursShow
import com.example.weatherapp.DataPackage.WeatherInSelectedCity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.RecyclerDaysBinding
import com.example.weatherapp.databinding.RecyclerHourBinding
import com.example.weatherapp.databinding.RecyclerWeatherBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.round

class RecyclerWeatherAdapter(val getData: WeatherInSelectedCity, var getHours: ArrayList<HoursShow>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val SHOW_WEATHER = 0
    val SHOW_HOUR = 1
    val SHOW_DAYS = 2

    inner class ShowWeatherViewHolder(private val binding: RecyclerWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bindShowWeatherView() {

                binding.ConditionTextView.text = getData.current.condition.text

                binding.MinMaxTempTextView.text = "L:${round(getData.forecast.forecastday[0].day.mintemp_c).toInt()}°  H:${round(getData.forecast.forecastday[0].day.maxtemp_c).toInt()}°"
            }

        }

    inner class ShowHourViewHolder(private val binding: RecyclerHourBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
            //binding.recyclerhorizontalhours.setHasFixedSize(true)
            binding.recyclerhorizontalhours.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            }

            fun bindShowHourView() {

                    val adapter = RecyclerHourAdapter(getHours)
                    binding.recyclerhorizontalhours.adapter = adapter



            }

    }

    inner class ShowDaysViewHolder(private val binding: RecyclerDaysBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                //binding.ForecastDaysRecyclerView.setHasFixedSize(true)
                binding.ForecastDaysRecyclerView.isNestedScrollingEnabled = true
                binding.ForecastDaysRecyclerView.layoutManager =
                    LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            }


            fun bindShowDaysView() {
                val adapter = RecyclerDaysAdapter(getData.forecast.forecastday)
                binding.ForecastDaysRecyclerView.adapter = adapter
            }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            SHOW_WEATHER ->
                R.layout.recycler_weather
            SHOW_HOUR ->
                R.layout.recycler_hour
            else ->
                R.layout.recycler_days
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.recycler_weather -> {
                val binding = RecyclerWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShowWeatherViewHolder(binding)
            }
            R.layout.recycler_hour -> {
                val binding = RecyclerHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShowHourViewHolder(binding)
            }
            else -> {
                val binding = RecyclerDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShowDaysViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowWeatherViewHolder -> {
                (holder as ShowWeatherViewHolder).bindShowWeatherView()
            }
            is ShowHourViewHolder -> {
                holder.bindShowHourView()
            }
            else ->{
                (holder as ShowDaysViewHolder).bindShowDaysView()
            }
        }
    }


}