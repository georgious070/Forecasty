package com.android.forecasty.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.android.forecasty.App
import com.android.forecasty.Const
import com.android.forecasty.data.api.CitiesCycleApi
import com.android.forecasty.data.api.CurrentTownApi
import com.android.forecasty.data.database.AppDatabase
import com.android.forecasty.data.database.dao.HistoryDao
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApp(context: Context): App
}