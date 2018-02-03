package com.forecast.android.forecasty.di.component

import com.forecast.android.forecasty.App
import com.forecast.android.forecasty.di.module.AppModule
import com.forecast.android.forecasty.ui.home.TownForecastActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(app: App)

    fun inject(townForecastActivity: TownForecastActivity)
}