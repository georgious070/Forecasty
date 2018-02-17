package com.android.forecasty.ui.cities

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.data.model.cycle.ListItem
import com.android.forecasty.domain.CitiesCycleInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CitiesCycleViewModel @Inject constructor(
        val citiesCycleInteractor: CitiesCycleInteractor) : ViewModel() {

    private var cities: MutableLiveData<MutableList<ListItem>>? = null
    private var compositeDisposable = CompositeDisposable()

    fun getData(latitude: Int, longitude: Int): MutableLiveData<MutableList<ListItem>> {
        if (cities == null) {
            cities = MutableLiveData()
            loadCities(latitude, longitude)
        }
        return cities as MutableLiveData<MutableList<ListItem>>
    }

    fun loadCities(latitude: Int, longitude: Int) {
        compositeDisposable.add(
                citiesCycleInteractor.getCities(latitude, longitude)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { response -> cities!!.value = response })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}