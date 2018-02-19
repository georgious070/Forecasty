package com.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CurrentTownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.android.forecasty.data.repository.town.DataEveryThirdHourWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class CurrentTownViewModel @Inject constructor(
        val currentTownInteractor: CurrentTownInteractor) : ViewModel() {

    private var currentDayWeather: MutableLiveData<MutableList<DataEveryThirdHourWeather>>? = null
    var compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<MutableList<DataEveryThirdHourWeather>> {
        if (currentDayWeather == null) {
            currentDayWeather = MutableLiveData()
            loadTemperature()
        }
        return currentDayWeather as MutableLiveData<MutableList<DataEveryThirdHourWeather>>
    }

    fun loadTemperature() {
        compositeDisposable.add(currentTownInteractor.getName()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    currentDayWeather!!.value = response
                }))
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}