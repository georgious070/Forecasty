package com.android.forecasty.data.model.town

import com.google.gson.annotations.SerializedName

data class CurrentLocation(@SerializedName("dt") val dt: Int,
                           @SerializedName("coord") val coord: Coord,
                           @SerializedName("weather") val weather: List<WeatherItem?>,
                           @SerializedName("name") val name: String,
                           @SerializedName("cod") val cod: Int,
                           @SerializedName("main") val main: Main,
                           @SerializedName("clouds") val clouds: Clouds,
                           @SerializedName("id") val id: Int,
                           @SerializedName("sys") val sys: Sys,
                           @SerializedName("base") val base: String,
                           @SerializedName("wind") val wind: Wind)