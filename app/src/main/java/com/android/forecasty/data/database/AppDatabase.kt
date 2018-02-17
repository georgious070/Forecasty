package com.android.forecasty.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.forecasty.Const
import com.android.forecasty.data.database.dao.HistoryDao
import com.android.forecasty.data.database.entity.HistoryEntity

@Database(entities = arrayOf(HistoryEntity::class),
        version = Const.DB.DATABASE_VERSION,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}