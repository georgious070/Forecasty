package com.android.forecasty.domain

import com.android.forecasty.data.model.cycle.ListItem
import com.android.forecasty.data.repository.cities.CitiesCycleRepository
import io.reactivex.Flowable
import javax.inject.Inject

class CitiesCycleInteractor @Inject constructor(val citiesCycleRepository: CitiesCycleRepository){

    fun getCities(lat:Int, long:Int): Flowable<MutableList<ListItem>>
            = citiesCycleRepository.getCities(lat,long)
}