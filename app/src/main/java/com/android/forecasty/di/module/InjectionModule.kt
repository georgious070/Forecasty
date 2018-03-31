package com.android.forecasty.di.module

import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.android.forecasty.ui.home.CurrentTownActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectionModule {

    @ContributesAndroidInjector
    abstract fun bindTownActivity(): CurrentTownActivity

    @ContributesAndroidInjector
    abstract fun bindCitiesCycleActivity(): CitiesCycleActivity
}