package com.android.forecasty.data.model.town

import com.google.gson.annotations.SerializedName

data class Sys(@SerializedName("sunrise") val sunrise: Int,
               @SerializedName("sunset") val sunset: Int,
               @SerializedName("message") val message: Double)