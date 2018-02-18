package com.android.forecasty.domain

import com.android.forecasty.data.repository.town.CurrentTownRepository
import com.android.forecasty.data.repository.town.DataEveryThirdHourWeather
import io.reactivex.Flowable
import javax.inject.Inject

class CurrentTownInteractor @Inject constructor(val currentTownRepository: CurrentTownRepository) {

    fun getName(): Flowable<MutableList<DataEveryThirdHourWeather>> =
            currentTownRepository.getWeatherByCoord()
}