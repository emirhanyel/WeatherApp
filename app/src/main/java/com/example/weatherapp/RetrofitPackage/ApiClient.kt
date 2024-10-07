package com.example.weatherapp.RetrofitPackage

import com.example.weatherapp.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit as Retrofit
    }
}