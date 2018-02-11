package com.android.forecasty.data.repository.cities

import com.android.forecasty.Constants
import com.android.forecasty.data.api.CitiesCycleApi
import com.android.forecasty.data.model.cycle.ListItem
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesCycleRepository @Inject constructor(val citiesCycleApi: CitiesCycleApi) {

    fun getCities(lat: Int, lon: Int): Flowable<MutableList<ListItem>> {
        return citiesCycleApi.getWeatherInCycle(lat, lon, 50, Constants.APPID_KEY)
                .map { response ->
                    var listItem: MutableList<ListItem> = ArrayList()
                    for (item in response.list) {
                        listItem.add(item)
                    }
                    listItem
                }
                .subscribeOn(Schedulers.io())
    }
}