package com.android.forecasty.di.component

import com.android.forecasty.App
import com.android.forecasty.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityInjectionModule::class,
    ApiModule::class,
    DatabaseModule::class,
    LocationModule::class,
    NavigationModule::class,
    ViewModelModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}