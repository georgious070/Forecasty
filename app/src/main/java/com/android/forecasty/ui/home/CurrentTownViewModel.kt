package com.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CurrentTownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.android.forecasty.data.repository.town.WeatherDescription
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class CurrentTownViewModel @Inject constructor(
        val currentTownInteractor: CurrentTownInteractor) : ViewModel() {

    private var currentWeather: MutableLiveData<WeatherDescription>? = null
    var compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<WeatherDescription> {
        if (currentWeather == null) {
            currentWeather = MutableLiveData()
            loadTemperature()
        }
        return currentWeather as MutableLiveData<WeatherDescription>
    }

    fun loadTemperature() {
        compositeDisposable.add(currentTownInteractor.getName()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    currentWeather!!.value = response
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}