package com.android.forecasty.data.api

import com.android.forecasty.data.model.town.CurrentLocation
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentTownApi {

    @GET("data/2.5/weather")
    fun getWeatherByLocation(
            @Query("lat") lat: Int,
            @Query("lon") lon: Int,
            @Query("APPID") appidKey: String): Flowable<CurrentLocation>
}