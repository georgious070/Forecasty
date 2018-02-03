package com.forecast.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.forecast.android.forecasty.domain.TownInteractor
import java.util.*
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.forecast.android.forecasty.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class TownViewModel @Inject constructor(val townInteractor: TownInteractor) : ViewModel() {

    private var name: MutableLiveData<String>? = null
    private lateinit var disposable: Disposable

    fun getData(): LiveData<String> {
        if (name == null) {
            name = MutableLiveData()
            loadName()
        }
        return name as MutableLiveData<String>
    }

    fun loadName() {
        disposable = townInteractor.getName()
                .subscribe({ response ->
                    name!!.postValue(response)
                })
    }
}