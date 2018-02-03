package com.forecast.android.forecasty

import android.app.Application
import com.forecast.android.forecasty.di.component.AppComponent
import com.forecast.android.forecasty.di.module.AppModule
import com.forecast.android.forecasty.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary


class App : Application() {

    companion object {
        lateinit var app: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        app = this
        appComponent.inject(this)
    }

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}