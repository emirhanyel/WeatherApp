package com.example.weatherapp.RetrofitPackage

import com.example.weatherapp.DataPackage.WeatherInSelectedCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiService {
    @GET("v1/forecast.json")
    fun getCurrentWeather(@Query("key") key: String, @Query("q") q: String, @Query("days") days: Int, @Query("aqi") aqi: String, @Query("alerts") alerts: String ): Call<WeatherInSelectedCity>
}