package com.example.weatherapp.DataPackage

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class WeatherInSelectedCity (
    val location: Location,
    val current: Current,
    val forecast: Forecast
): Serializable

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String
): Serializable

data class Current(
    val last_updated_epoch: Int,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Double,
    val pressure_in: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val uv: Double,
    val air_quality: Air_quality
): Serializable

data class Forecast(
    val forecastday: List<Forecastday>
): Serializable

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
): Serializable

data class Air_quality(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm2_5: Double,
    val pm10: Double
): Serializable

data class Forecastday(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
): Serializable

data class Day(
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val maxwind_mph: Double,
    val maxwind_kph: Double,
    val avghumidity: Int,
    val daily_will_it_rain: Int,
    val daily_chance_of_rain: Int,
    val daily_will_it_snow: Int,
    val daily_chance_of_snow: Int,
    val condition: Condition,
    val uv: Double,
    val air_quality: Air_quality
): Serializable

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moon_phase: String,
    val moon_illumination: Int,
    val is_moon_up: Int,
    val is_sun_up: Int
): Serializable

data class Hour(
    val time_epoch: Int,
    val time: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Double,
    val pressure_in: Double,
    val snow_cm: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val will_it_rain: Int,
    val chance_of_rain: Int,
    val will_it_snow: Int,
    val chance_of_snow: Int,
    val uv: Double,
    val air_quality: Air_quality
): Serializable