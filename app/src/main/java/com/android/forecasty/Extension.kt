package com.android.forecasty

import android.annotation.SuppressLint
import com.android.forecasty.data.model.town.ListItem
import com.android.forecasty.data.repository.town.DataEveryThirdHourWeather
import com.android.forecasty.data.repository.town.DayData
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
private val dateFormat = SimpleDateFormat("yyyy MM dd")

fun MutableList<DataEveryThirdHourWeather>.addDayData(date: ListItem) {
    this.add(
            DataEveryThirdHourWeather(
                    date.timeUTC,
                    date.main.temp,
                    date.weather[0]!!.description,
                    date.weather[0]!!.icon))
}

fun MutableList<DayData>.addNewDay(date: ListItem,
                                   cityName: String,
                                   latitude: Double,
                                   longitude: Double) {
    this.add(DayData(
            convertUTCtoDate(date.timeUTC),
            cityName,
            latitude.toInt(),
            longitude.toInt(),
            mutableListOf(
                    DataEveryThirdHourWeather(
                            date.timeUTC,
                            date.main.temp,
                            date.weather[0]!!.description,
                            date.weather[0]!!.icon))))
}

fun Any.convertUTCtoDate(utc: Int): String {
    var epochDate = Date(utc.toLong() * 1000)
    dateFormat.timeZone = TimeZone.getDefault()
    return dateFormat.format(epochDate)
}