package com.android.forecasty.domain

import com.android.forecasty.data.repository.town.TownRepository
import com.android.forecasty.data.repository.town.WeatherDescription
import io.reactivex.Flowable
import javax.inject.Inject

class CurrentTownInteractor @Inject constructor(val townRepository: TownRepository) {

    fun getName(): Flowable<WeatherDescription> = townRepository.getWeatherByCoord()
}