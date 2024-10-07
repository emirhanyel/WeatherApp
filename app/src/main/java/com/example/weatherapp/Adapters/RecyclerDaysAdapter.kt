package com.example.weatherapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.DataPackage.Forecastday
import com.example.weatherapp.DataPackage.HoursShow
import com.example.weatherapp.R
import com.example.weatherapp.databinding.RecyclerDaysItemsBinding
import com.example.weatherapp.databinding.RecyclerHourItemsBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.round

class RecyclerDaysAdapter(private var forecastDays: List<Forecastday>):
    RecyclerView.Adapter<RecyclerDaysAdapter.RecyclerDaysViewHolder>() {

    lateinit var context: Context
    var showDay : String = "Mon"


    class RecyclerDaysViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = RecyclerDaysItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerDaysViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_days_items,parent,false)
        context = parent.context
        return RecyclerDaysViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastDays.size
    }

    override fun onBindViewHolder(holder: RecyclerDaysViewHolder, position: Int) {
        holder.binding.apply {

            if(position==0){
                WhichDay.text = "Today"
                Glide.with(context).load("https:"+forecastDays[position].day.condition.icon).override(115,115).into(WhichDayImage)
                WhichDayLow.text = "L:" + round(forecastDays[position].day.mintemp_c).toInt() + "°"
                WhichDayHigh.text = "H:" + round(forecastDays[position].day.maxtemp_c).toInt() + "°"
            }
            else{
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(forecastDays[position].date, formatter)
                val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                if(dayOfWeek == "Monday"){
                    showDay = "Mon"
                }
                else if(dayOfWeek == "Tuesday"){
                    showDay = "Tue"
                }
                else if(dayOfWeek == "Wednesday"){
                    showDay = "Wed"
                }
                else if(dayOfWeek == "Thursday"){
                    showDay = "Thu"
                }
                else if(dayOfWeek == "Friday"){
                    showDay = "Fri"
                }
                else if(dayOfWeek == "Saturday"){
                    showDay = "Sat"
                }
                else{
                    showDay = "Sun"
                }

                WhichDay.text = showDay
                Glide.with(context).load("https:"+forecastDays[position].day.condition.icon).override(115,115).into(WhichDayImage)
                WhichDayLow.text = "L:" + round(forecastDays[position].day.mintemp_c).toInt() + "°"
                WhichDayHigh.text = "H:" + round(forecastDays[position].day.maxtemp_c).toInt() + "°"
            }

        }
    }

}



