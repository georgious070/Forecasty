package com.android.forecasty.domain

import com.android.forecasty.data.repository.town.CurrentTownRepository
import com.android.forecasty.data.repository.town.WeatherDescription
import io.reactivex.Flowable
import javax.inject.Inject

class CurrentTownInteractor @Inject constructor(val currentTownRepository: CurrentTownRepository) {

    fun getName(): Flowable<WeatherDescription> = currentTownRepository.getWeatherByCoord()
}