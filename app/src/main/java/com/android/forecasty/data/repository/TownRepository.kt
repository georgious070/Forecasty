package com.android.forecasty.data.repository

import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherApi
import com.android.forecasty.data.model.Main
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TownRepository @Inject constructor(val weatherApi: WeatherApi) {

    fun getWeatherByCoord(): Flowable<Double> =
            weatherApi.getWeatherByLocation(20, 120, Constants.APPID_KEY)
                    .subscribeOn(Schedulers.io())
                    .map { response ->
                        response.main!!.temp
                    }
}