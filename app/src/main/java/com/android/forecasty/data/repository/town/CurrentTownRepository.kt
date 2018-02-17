package com.android.forecasty.data.repository.town

import android.annotation.SuppressLint
import com.android.forecasty.App
import com.android.forecasty.Const
import com.android.forecasty.data.api.CurrentTownApi
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentTownRepository @Inject constructor(val currentTownApi: CurrentTownApi,
                                                val locationRequest: LocationRequest) {

    @SuppressLint("MissingPermission")
    fun getWeatherByCoord(): Flowable<WeatherDescription> {
        var rxLocation = RxLocation(App.app)
        return rxLocation.location().updates(locationRequest)
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap { location ->
                    currentTownApi.getWeatherByLocation(
                            location.latitude.toInt(),
                            location.longitude.toInt(),
                            Const.Api.APPID_KEY)
                            .map { response ->
                                WeatherDescription(response.main.temp.toString(),
                                        response.name,
                                        response.coord.lat.toInt(),
                                        response.coord.lon.toInt())
                            }
                            .subscribeOn(Schedulers.io())
                }.subscribeOn(Schedulers.io())
    }
}