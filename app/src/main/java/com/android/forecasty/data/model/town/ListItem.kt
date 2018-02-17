package com.android.forecasty.data.model.town

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("dt")
                    val timeUTC: Int,
                    @SerializedName("rain")
                    val rain: Rain,
                    @SerializedName("dt_txt")
                    val timeDATE: String,
                    @SerializedName("weather")
                    val weather: List<WeatherItem?>,
                    @SerializedName("main")
                    val main: Main,
                    @SerializedName("clouds")
                    val clouds: Clouds,
                    @SerializedName("sys")
                    val sys: Sys,
                    @SerializedName("wind")
                    val wind: Wind)