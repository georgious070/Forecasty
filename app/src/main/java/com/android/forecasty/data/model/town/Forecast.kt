package com.android.forecasty.data.model.town

import com.google.gson.annotations.SerializedName

data class Forecast(@SerializedName("city")
                    val city: City,
                    @SerializedName("cnt")
                    val cnt: Int,
                    @SerializedName("cod")
                    val cod: String,
                    @SerializedName("message")
                    val message: Double,
                    @SerializedName("list")
                    val listOfEveryThirdTime: List<ListItem?>)