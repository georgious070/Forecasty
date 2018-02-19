package com.android.forecasty.data.repository.town


data class DayData(val day: String,
                   val cityName: String,
                   val latitude: Int,
                   val longitude: Int,
                   val listOfEveryThirdHourWeather: MutableList<DataEveryThirdHourWeather>)