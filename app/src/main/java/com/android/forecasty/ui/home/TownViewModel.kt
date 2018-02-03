package com.android.forecasty.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.forecasty.domain.TownInteractor
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


class TownViewModel @Inject constructor(val townInteractor: TownInteractor) : ViewModel() {

    private var name: MutableLiveData<String>? = null
    private lateinit var disposable: Disposable

    fun getData(): LiveData<String> {
        if (name == null) {
            name = MutableLiveData()
            disposable = townInteractor.getName()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        name!!.postValue(response.toString())
                    })
        }
        return name as MutableLiveData<String>
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}