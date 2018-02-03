package com.forecast.android.forecasty.domain

import com.forecast.android.forecasty.data.repository.TownRepository
import io.reactivex.Flowable
import javax.inject.Inject


class TownInteractor @Inject constructor(val townRepository: TownRepository) {

    fun getName(): Flowable<String> = townRepository.getName()
}