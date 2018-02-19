package com.android.forecasty.data.repository.town

import android.annotation.SuppressLint
import com.android.forecasty.Const
import com.android.forecasty.addDayData
import com.android.forecasty.addNewDay
import com.android.forecasty.convertUTCtoDate
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

    @SuppressLint("MissingPermission")
    fun getWeatherByCoord(): Flowable<MutableList<DayData>> {
        return rxLocation.location().updates(locationRequest)
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap { location ->
                    currentTownApi.getWeatherByLocation(
                            location.latitude.toInt(),
                            location.longitude.toInt(),
                            Const.Api.APPID_KEY)
                            .map { response ->
                                val listOfDays = mutableListOf<DayData>()
                                var counter = 0
                                var firstDate = response.listOfEveryThirdTime[0]
                                listOfDays.addNewDay(
                                        firstDate!!,
                                        response.city.name,
                                        location.latitude,
                                        location.longitude)

                                for (date in response.listOfEveryThirdTime) {
                                    if (listOfDays[counter].day == convertUTCtoDate(date!!.timeUTC)) {
                                        listOfDays[counter].listOfEveryThirdHourWeather.addDayData(date)
                                    } else {
                                        counter++
                                        listOfDays.addNewDay(
                                                date,
                                                response.city.name,
                                                location.latitude,
                                                location.longitude)
                                    }
                                }
                                listOfDays
                            }.subscribeOn(Schedulers.io())
                }.subscribeOn(Schedulers.io())
    }
}