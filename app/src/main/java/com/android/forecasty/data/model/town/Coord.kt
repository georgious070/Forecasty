package com.android.forecasty.data.model.town

import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon") val lon: Int,
                 @SerializedName("lat") val lat: Int)