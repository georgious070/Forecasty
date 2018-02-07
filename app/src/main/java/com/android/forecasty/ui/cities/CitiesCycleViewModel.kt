package com.android.forecasty.ui.cities

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.CitiesCycleInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CitiesCycleViewModel @Inject constructor(
       val citiesCycleInteractor: CitiesCycleInteractor) : ViewModel() {

    private var cities: MutableLiveData<List<String>>? = null

    fun getData(latitude:Int, longitude:Int):MutableLiveData<List<String>>{
        if(cities==null){
            cities = MutableLiveData()
            loadCities(latitude, longitude)
        }
        return cities as MutableLiveData<List<String>>
    }

    fun loadCities(latitude: Int, longitude: Int){
        citiesCycleInteractor.getCities(latitude,longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({})
    }
}