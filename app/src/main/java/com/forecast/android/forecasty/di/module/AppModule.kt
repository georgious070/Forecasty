package com.forecast.android.forecasty.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule constructor(val app: Context) {

    @Provides
    @Singleton
    fun provideApp() = app
}