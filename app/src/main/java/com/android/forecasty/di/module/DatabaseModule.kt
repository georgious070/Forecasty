package com.android.forecasty.di.module

import android.arch.persistence.room.Room
import com.android.forecasty.App
import com.android.forecasty.Const
import com.android.forecasty.data.database.AppDatabase
import com.android.forecasty.data.database.dao.HistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, Const.DB.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao =
            appDatabase.historyDao()
}