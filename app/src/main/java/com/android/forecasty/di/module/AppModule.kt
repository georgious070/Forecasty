package com.android.forecasty.di.module

import android.arch.persistence.room.Room
import com.android.forecasty.App
import com.android.forecasty.Const
import com.android.forecasty.data.api.CitiesCycleApi
import com.android.forecasty.data.api.CurrentTownApi
import com.android.forecasty.data.database.AppDatabase
import com.android.forecasty.data.database.dao.HistoryDao
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule constructor(val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(Const.Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideLocationApi(retrofit: Retrofit): CurrentTownApi =
            retrofit.create(CurrentTownApi::class.java)

    @Provides
    @Singleton
    fun provideCitiesInCycleApi(retrofit: Retrofit): CitiesCycleApi =
            retrofit.create(CitiesCycleApi::class.java)

    @Provides
    @Singleton
    fun provideLocationRequest(): LocationRequest =
            LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(1_800_000)

    @Provides
    @Singleton
    fun provideRxLocation(app: App): RxLocation =
            RxLocation(app)

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, Const.DB.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao =
            appDatabase.historyDao()
}