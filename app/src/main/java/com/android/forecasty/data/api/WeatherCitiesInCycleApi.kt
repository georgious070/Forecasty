package com.android.forecasty.data.api

import com.android.forecasty.data.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherCitiesInCycleApi {

    @GET("data/2.5/find")
    fun getWeatherInCycle(
            @Query("lat") lat: Int,
            @Query("lon") lon: Int,
            @Query("cnt") count: Int,
            @Query("APPID") appidKey: String): Flowable<Response>
}