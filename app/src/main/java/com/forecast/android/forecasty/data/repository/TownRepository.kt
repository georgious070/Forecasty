package com.forecast.android.forecasty.data.repository

import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TownRepository @Inject constructor(){

    fun getName():Flowable<String>{
        return Flowable.just("HELLO")
    }
}