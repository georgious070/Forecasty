package com.android.forecasty.data.model.location

import com.google.gson.annotations.SerializedName

data class WeatherItem(@SerializedName("icon") val icon: String,
                       @SerializedName("description") val description: String,
                       @SerializedName("main") val main: String,
                       @SerializedName("id") val id: Int)