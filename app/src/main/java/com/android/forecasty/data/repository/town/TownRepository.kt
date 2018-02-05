package com.android.forecasty.data.repository.town

import android.annotation.SuppressLint
import com.android.forecasty.App
import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherCurrentLocationApi
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TownRepository @Inject constructor(val weatherCurrentLocationApi: WeatherCurrentLocationApi,
                                         val locationRequest: LocationRequest) {

    @SuppressLint("MissingPermission")
    fun getWeatherByCoord(): Flowable<WeatherDescription> {
        var rxLocation = RxLocation(App.app)
        return rxLocation.location().updates(locationRequest)
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap { location ->
                    weatherCurrentLocationApi.getWeatherByLocation(
                            location.latitude.toInt(),
                            location.longitude.toInt(),
                            Constants.APPID_KEY)
                            .map { response ->
                                WeatherDescription(response.main.temp.toString(),
                                        response.name,
                                        response.coord.lat,
                                        response.coord.lon)
                            }
                            .subscribeOn(Schedulers.io())
                }
    }
}