package com.android.forecasty.domain

import com.android.forecasty.data.repository.TownRepository
import com.android.forecasty.data.repository.WeatherDescription
import io.reactivex.Flowable
import javax.inject.Inject

class TownInteractor @Inject constructor(val townRepository: TownRepository) {

    fun getName(): Flowable<WeatherDescription> = townRepository.getWeatherByCoord()
}