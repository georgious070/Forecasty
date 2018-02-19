package com.android.forecasty.data.repository.town

data class DataEveryThirdHourWeather constructor(var timeUTC: Int,
                                                 var temp: Double,
                                                 var weatherDescription: String,
                                                 var weatherIcon: String)