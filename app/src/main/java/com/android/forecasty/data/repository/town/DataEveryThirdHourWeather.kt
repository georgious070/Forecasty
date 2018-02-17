package com.android.forecasty.data.repository.town

import android.graphics.drawable.AdaptiveIconDrawable

data class DataEveryThirdHourWeather constructor(var timeUTC: Int,
                                                 var temp: String,
                                                 var weatherDescription: String,
                                                 var weatherIcon: String,
                                                 var cityName: String,
                                                 var latitude: Int,
                                                 var longitude: Int)