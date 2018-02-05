package com.android.forecasty.data.api

import com.android.forecasty.data.model.location.CurrentLocation
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherCurrentLocationApi {

    @GET("data/2.5/weather")
    fun getWeatherByLocation(
            @Query("lat") lat: Int,
            @Query("lon") lon: Int,
            @Query("APPID") appidKey: String): Flowable<CurrentLocation>
}