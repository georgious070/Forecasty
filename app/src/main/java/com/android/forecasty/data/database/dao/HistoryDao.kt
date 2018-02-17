package com.android.forecasty.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.android.forecasty.Const
import com.android.forecasty.data.database.entity.HistoryEntity
import io.reactivex.Flowable

@Dao
interface HistoryDao {

    @Insert
    fun insertDayWeatherDescription(historyEntity: HistoryEntity)

    @Query("SELECT * FROM " + Const.DB.HISTORY_TABLE_NAME)
    fun queryHistory(): Flowable<MutableList<HistoryEntity>>
}