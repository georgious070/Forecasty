package com.forecast.android.forecasty.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.forecast.android.forecasty.annotations.ViewModelKey
import com.forecast.android.forecasty.ui.home.TownViewModel
import com.forecast.android.forecasty.ui.home.TownViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TownViewModel::class)
    abstract fun bindTownViewModel(townViewModel: TownViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: TownViewModelFactory): ViewModelProvider.Factory
}