package com.android.forecasty.di.module

import com.android.forecasty.Const
import com.android.forecasty.data.api.CitiesCycleApi
import com.android.forecasty.data.api.CurrentTownApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

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
}