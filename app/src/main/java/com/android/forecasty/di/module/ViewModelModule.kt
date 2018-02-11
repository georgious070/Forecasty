package com.android.forecasty.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.forecasty.annotations.ViewModelKey
import com.android.forecasty.ui.home.CurrentTownViewModel
import com.android.forecasty.ui.ForecastViewModelFactory
import com.android.forecasty.ui.cities.CitiesCycleViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentTownViewModel::class)
    abstract fun bindCurrentTownViewModel(townViewModel: CurrentTownViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CitiesCycleViewModel::class)
    abstract fun bindCitiesCycleViewModel(citiesCycleViewModel: CitiesCycleViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ForecastViewModelFactory): ViewModelProvider.Factory
}