package com.android.forecasty.data.repository

import android.annotation.SuppressLint
import com.android.forecasty.App
import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherApi
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TownRepository @Inject constructor(val weatherApi: WeatherApi,
                                         val locationRequest: LocationRequest) {

    @SuppressLint("MissingPermission")
    fun getWeatherByCoord(): Flowable<WeatherDescription> {
        var rxLocation = RxLocation(App.app)
        return rxLocation.location().updates(locationRequest)
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap { location ->
                    weatherApi.getWeatherByLocation(
                            location.latitude.toInt(),
                            location.longitude.toInt(),
                            Constants.APPID_KEY)
                            .map { response ->
                                WeatherDescription(response.main.temp.toString(), response.name)
                            }
                            .subscribeOn(Schedulers.io())
                }
    }
}