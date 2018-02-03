package com.android.forecasty.data.api

import com.android.forecasty.data.model.Main
import com.android.forecasty.data.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getWeatherByLocation(
            @Query("lat") lat: Int,
            @Query("lon") lon: Int,
            @Query("APPID") appidKey: String): Flowable<Response>
}