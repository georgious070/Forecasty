package com.android.forecasty.di.module

import android.content.Context
import android.location.Location
import com.android.forecasty.App
import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherApi
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import dagger.Module
import dagger.Provides
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule constructor(val app: Context) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): WeatherApi =
            retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideLocationRequest(): LocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(1_800_000)
}