package com.android.forecasty.data.repository.cities

import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherCitiesInCycleApi
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CitiesCycleRepository @Inject constructor(val weatherCitiesInCycleApi: WeatherCitiesInCycleApi) {

    var listofCitiesInCycleApi: MutableList<String> = ArrayList<String>()

    fun getCities(lat: Int, lon: Int): Flowable<MutableList<String>> =
            weatherCitiesInCycleApi.getWeatherInCycle(lat, lon, 50, Constants.APPID_KEY)
                    .map { response ->
                        for (item in response.list.indices) {
                            listofCitiesInCycleApi.add(response.list.get(item).name)
                        }
                        listofCitiesInCycleApi
                    }.subscribeOn(Schedulers.io())
}