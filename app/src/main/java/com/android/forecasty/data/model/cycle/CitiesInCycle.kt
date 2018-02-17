package com.android.forecasty.data.model.cycle

import com.google.gson.annotations.SerializedName

data class CitiesInCycle(@SerializedName("count") val count: Int,
                         @SerializedName("cod") val cod: String,
                         @SerializedName("message") val message: String,
                         @SerializedName("list") val list: List<ListItem>)