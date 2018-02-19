package com.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CurrentTownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.android.forecasty.data.repository.town.DataEveryThirdHourWeather
import com.android.forecasty.data.repository.town.DayData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class CurrentTownViewModel @Inject constructor(
        val currentTownInteractor: CurrentTownInteractor) : ViewModel() {

    private var currentDayWeather: MutableLiveData<MutableList<DayData>>? = null
    private var compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<MutableList<DayData>> {
        if (currentDayWeather == null) {
            currentDayWeather = MutableLiveData()
            loadTemperature()
        }
        return currentDayWeather as MutableLiveData<MutableList<DayData>>
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