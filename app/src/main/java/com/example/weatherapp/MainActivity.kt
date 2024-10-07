package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.weatherapp.DataPackage.WeatherInSelectedCity
import com.example.weatherapp.RetrofitPackage.ApiClient
import com.example.weatherapp.RetrofitPackage.ApiService
import com.example.weatherapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var apiService: ApiService
    var apiResponse: WeatherInSelectedCity? = null

    lateinit var editText: EditText
    lateinit var searchButton: Button
    var language: String = "Eng"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        editText = binding.SearchCityEditText
        searchButton = binding.SearchButton


        searchButton.setOnClickListener {
            apiService = ApiClient.getClient().create(ApiService::class.java)
            var request = apiService.getCurrentWeather(Constants.API_KEY,editText.text.toString(),10, "yes", "yes")
            request.enqueue(object : Callback<WeatherInSelectedCity> {

                override fun onResponse(call: Call<WeatherInSelectedCity>, response: Response<WeatherInSelectedCity>) {
                    if (response.isSuccessful) {
                        apiResponse = response.body()
                        if (apiResponse != null) {
                            val intent = Intent(this@MainActivity, WeatherDetailActivity::class.java).apply {
                                putExtra("DataToWeatherDetailActivity", apiResponse!!)
                            }
                            startActivity(intent)

                        }
                    } else {
                        Log.e("MainActivity", "Error: ${response.code()} ${response.message()}")
                        Toast.makeText(this@MainActivity, "City Not Found!", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<WeatherInSelectedCity>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }


    }
}