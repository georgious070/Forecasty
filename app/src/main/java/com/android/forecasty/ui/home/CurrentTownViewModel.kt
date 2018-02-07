package com.android.forecasty.ui.home

import android.Manifest
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CurrentTownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.android.forecasty.App
import com.android.forecasty.data.repository.town.WeatherDescription
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class CurrentTownViewModel @Inject constructor(
        val currentTownInteractor: CurrentTownInteractor) : ViewModel() {

    private var currentWeather: MutableLiveData<WeatherDescription>? = null
    private lateinit var disposable: Disposable

    fun getData(): LiveData<WeatherDescription> {
        if (currentWeather == null) {
            currentWeather = MutableLiveData()
            loadTemperature()
        }
        return currentWeather as MutableLiveData<WeatherDescription>
    }

    fun loadTemperature() {
        if (ContextCompat.checkSelfPermission(App.app, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            disposable = currentTownInteractor.getName()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        currentWeather!!.value = response
                    })
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}