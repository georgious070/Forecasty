package com.android.forecasty.di.component

import com.android.forecasty.App
import com.android.forecasty.di.module.AppModule
import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.android.forecasty.ui.home.CurrentTownActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(app: App)

    fun inject(currentTownActivity: CurrentTownActivity)

    fun inject(citiesCycleActivity: CitiesCycleActivity)
}