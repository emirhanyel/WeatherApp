package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapters.RecyclerWeatherAdapter
import com.example.weatherapp.DataPackage.HoursShow
import com.example.weatherapp.DataPackage.WeatherInSelectedCity
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.ActivityWeatherDetailBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round

class WeatherDetailActivity: AppCompatActivity(){
    private lateinit var binding: ActivityWeatherDetailBinding
    private lateinit var takeData: WeatherInSelectedCity
            lateinit var recyclerWeatherAdapter: RecyclerWeatherAdapter
            lateinit var BtnBack: Button
    var arrayListHours = ArrayList<HoursShow>()
    lateinit var checkhours: LocalDateTime
    lateinit var hoursShow: HoursShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        BtnBack = binding.buttonBack

        //binding.recyclerWeather.setHasFixedSize(true)
        binding.recyclerWeather.layoutManager = LinearLayoutManager(this@WeatherDetailActivity)


        takeData = intent.getSerializableExtra("DataToWeatherDetailActivity") as WeatherInSelectedCity

        println(takeData)
        binding.textView.text = takeData.location.name
        binding.textView2.text = round(takeData.current.temp_c).toInt().toString()+"\u00B0"

        val localtime = getTime(takeData.location.localtime)

        for(hour in takeData.forecast.forecastday[0].hour){
            checkhours = getTime(hour.time)
            if(localtime.hour == checkhours.hour){
                hoursShow = HoursShow("Now", hour.condition.icon, hour.temp_c)
                arrayListHours.add(hoursShow)
            }
            else if(localtime.hour < checkhours.hour){
                hoursShow = HoursShow(checkhours.hour.toString(), hour.condition.icon, hour.temp_c)
                arrayListHours.add(hoursShow)
            }

        }
        for(hour in takeData.forecast.forecastday[1].hour){
            checkhours = getTime(hour.time)
            if(localtime.hour >= checkhours.hour){
                hoursShow = HoursShow(checkhours.hour.toString(), hour.condition.icon, hour.temp_c)
                arrayListHours.add(hoursShow)
            }

        }

        recyclerWeatherAdapter = RecyclerWeatherAdapter(takeData, arrayListHours)
        binding.recyclerWeather.adapter = recyclerWeatherAdapter

        BtnBack.setOnClickListener {
            val intent = Intent(this@WeatherDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun getTime(time: String): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(time, formatter)
        return dateTime
    }
}