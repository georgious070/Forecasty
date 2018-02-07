package com.android.forecasty.data.repository.town

data class WeatherDescription constructor(var temp: String,
                                          var cityName: String,
                                          var latitude: Int,
                                          var longitude: Int)