package com.android.forecasty.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.forecasty.annotations.ViewModelKey
import com.android.forecasty.ui.home.CurrentTownViewModel
import com.android.forecasty.ui.ForecastViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentTownViewModel::class)
    abstract fun bindTownViewModel(townViewModel: CurrentTownViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ForecastViewModelFactory): ViewModelProvider.Factory
}