package com.android.forecasty.data.model.cycle

import com.android.forecasty.data.model.town.WeatherItem
import com.android.forecasty.data.model.town.Coord
import com.android.forecasty.data.model.town.Main
import com.android.forecasty.data.model.town.Clouds
import com.android.forecasty.data.model.town.Sys
import com.android.forecasty.data.model.town.Wind
import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("timeUTC") val dt: Int,
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