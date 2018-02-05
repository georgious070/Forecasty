package com.android.forecasty.data.model.cycle

import com.android.forecasty.data.model.location.WeatherItem
import com.android.forecasty.data.model.location.Coord
import com.android.forecasty.data.model.location.Main
import com.android.forecasty.data.model.location.Clouds
import com.android.forecasty.data.model.location.Sys
import com.android.forecasty.data.model.location.Wind
import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("dt") val dt: Int,
                    @SerializedName("rain") val rain: Any,
                    @SerializedName("coord") val coord: Coord,
                    @SerializedName("snow") val snow: Any,
                    @SerializedName("name") val name: String,
                    @SerializedName("weather") val weather: List<WeatherItem?>,
                    @SerializedName("main") val main: Main,
                    @SerializedName("id") val id: Int,
                    @SerializedName("clouds") val clouds: Clouds,
                    @SerializedName("sys") val sys: Sys,
                    @SerializedName("wind") val wind: Wind)