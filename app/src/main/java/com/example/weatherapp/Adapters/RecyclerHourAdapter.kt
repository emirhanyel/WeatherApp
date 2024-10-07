package com.example.weatherapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.DataPackage.HoursShow
import com.example.weatherapp.DataPackage.WeatherInSelectedCity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.RecyclerHourItemsBinding

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round

class RecyclerHourAdapter(private var getHours: ArrayList<HoursShow>):
    RecyclerView.Adapter<RecyclerHourAdapter.RecyclerHourViewHolder>() {

    lateinit var context: Context

    class RecyclerHourViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = RecyclerHourItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_hour_items,parent,false)
        context = parent.context
        return RecyclerHourViewHolder(view)
    }

    override fun getItemCount(): Int {
        return getHours.size
    }

    override fun onBindViewHolder(holder: RecyclerHourViewHolder, position: Int) {
        holder.binding.apply {
                println(getHours)
                println(getHours[position])
                HoursTextView.text = getHours[position].time
                Glide.with(context).load("https:"+getHours[position].image).override(115,115).into(HoursImageView)
                HoursDegree.text = round(getHours[position].degree).toInt().toString()+ "°"
            }
        }

    }



