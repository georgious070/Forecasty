package com.android.forecasty.data.repository.town

import android.annotation.SuppressLint
import com.android.forecasty.Const
import com.android.forecasty.data.api.CurrentTownApi
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentTownRepository @Inject constructor(val currentTownApi: CurrentTownApi,
                                                val locationRequest: LocationRequest,
                                                val rxLocation: RxLocation) {

    val dateFormat: SimpleDateFormat

    init {
        dateFormat = SimpleDateFormat("yyyy MM dd")
    }

    @SuppressLint("MissingPermission")
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
                                val hashMap = mutableMapOf<String, MutableList<DataEveryThirdHourWeather>>()
                                for (date in response.listOfEveryThirdTime) {
                                    if (hashMap.containsKey(convertUTCtoDate(date!!.timeUTC))) {
                                        hashMap.get(
                                                convertUTCtoDate(date.timeUTC))!!.add(
                                                DataEveryThirdHourWeather(
                                                        date.timeUTC,
                                                        date.main.temp,
                                                        date.weather[0]!!.description,
                                                        date.weather[0]!!.icon,
                                                        response.city.name,
                                                        location.latitude.toInt(),
                                                        location.longitude.toInt()))
                                    } else {
                                        hashMap.put(
                                                convertUTCtoDate(date.timeUTC),
                                                mutableListOf(DataEveryThirdHourWeather(
                                                        date.timeUTC,
                                                        date.main.temp,
                                                        date.weather[0]!!.description,
                                                        date.weather[0]!!.icon,
                                                        response.city.name,
                                                        location.latitude.toInt(),
                                                        location.longitude.toInt()
                                                )))
                                    }
                                }
                                listOfWeekTownWeather
                            }
                            .subscribeOn(Schedulers.io())
                }.subscribeOn(Schedulers.io())
    }

    fun convertUTCtoDate(utc: Int): String {
        var epochDate = Date(utc.toLong() * 1000)
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(epochDate)
    }
}