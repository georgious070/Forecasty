package com.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CurrentTownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.android.forecasty.data.repository.town.DataEveryThirdHourWeather
import com.android.forecasty.data.repository.town.DayData
import com.android.forecasty.ui.cities.LocationParamsData
import com.android.forecasty.ui.navigation.Screens
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router

class CurrentTownViewModel @Inject constructor(
        val currentTownInteractor: CurrentTownInteractor,
        val router: Router) : ViewModel() {

    private var currentDayWeather: MutableLiveData<MutableList<DayData>>? = null
    private var compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<MutableList<DayData>> {
        if (currentDayWeather == null) {
            currentDayWeather = MutableLiveData()
            loadTemperature()
        }
        return currentDayWeather as MutableLiveData<MutableList<DayData>>
    }

    fun toNextActivity(latitude: Int, longitude: Int) {
        router.navigateTo(Screens.CITIES_CYCLE, LocationParamsData(latitude, longitude))
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