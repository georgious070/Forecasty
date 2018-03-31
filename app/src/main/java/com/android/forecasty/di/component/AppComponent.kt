package com.android.forecasty.di.component

import com.android.forecasty.App
import com.android.forecasty.di.module.*
import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.android.forecasty.ui.home.CurrentTownActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    InjectionModule::class,
    ApiModule::class,
    DatabaseModule::class,
    LocationModule::class,
    NavigationModule::class,
    ViewModelModule::class]
)
interface AppComponent {

}