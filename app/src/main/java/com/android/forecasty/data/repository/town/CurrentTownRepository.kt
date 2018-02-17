package com.android.forecasty.data.repository.town

import android.annotation.SuppressLint
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
                                                val locationRequest: LocationRequest,
                                                val rxLocation: RxLocation) {


    fun getWeatherByCoord(): Flowable<MutableList<DataEveryThirdHourWeather>> {
        var listOfWeekTownWeather: MutableList<DataEveryThirdHourWeather> = ArrayList()
        return rxLocation.location().updates(locationRequest)
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap { location ->
                    currentTownApi.getWeatherByLocation(
                            location.latitude.toInt(),
                            location.longitude.toInt(),
                            Const.Api.APPID_KEY)
                            .map { response ->
                                for (date in response.listOfEveryThirdTime) {
                                    listOfWeekTownWeather.add(DataEveryThirdHourWeather(
                                            date!!.timeUTC,
                                            date.main.temp.toString(),
                                            date.weather.get(0)!!.description,
                                            date.weather.get(0)!!.icon,
                                            response.city.name,
                                            location.latitude.toInt(),
                                            location.longitude.toInt()))
                                }
                                listOfWeekTownWeather
                            }
                            .subscribeOn(Schedulers.io())
                }.subscribeOn(Schedulers.io())
    }
}