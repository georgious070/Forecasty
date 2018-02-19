package com.android.forecasty.data.repository.town


data class DayData(val day: String,
                   val listOfEveryThirdHourWeather: MutableList<DataEveryThirdHourWeather>)