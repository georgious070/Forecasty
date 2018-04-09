package com.android.forecasty.di.module

import com.android.forecasty.App
import com.android.forecasty.Const
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationRequest(): LocationRequest =
            LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(Const.Location.LOCATION_UPDATES_INTERVAL)

    @Provides
    @Singleton
    fun provideRxLocation(app: App): RxLocation =
            RxLocation(app)
}