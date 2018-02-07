package com.android.forecasty.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.android.forecasty.App
import com.android.forecasty.Constants
import com.android.forecasty.data.api.WeatherCitiesInCycleApi
import com.android.forecasty.data.api.WeatherCurrentLocationApi
import com.android.forecasty.data.database.AppDatabase
import com.android.forecasty.data.database.dao.HistoryDao
import com.google.android.gms.location.LocationRequest
import dagger.Module
import dagger.Provides
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
    fun provideLocationApi(retrofit: Retrofit): WeatherCurrentLocationApi =
            retrofit.create(WeatherCurrentLocationApi::class.java)

    @Provides
    @Singleton
    fun provideCitiesInCycleApi(retrofit: Retrofit): WeatherCitiesInCycleApi =
            retrofit.create(WeatherCitiesInCycleApi::class.java)

    @Provides
    @Singleton
    fun provideLocationRequest(): LocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(1_800_000)

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao = appDatabase.historyDao()
}