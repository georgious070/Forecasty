package com.android.forecasty.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.android.forecasty.Const

@Entity(tableName = Const.DB.HISTORY_TABLE_NAME)
data class HistoryEntity constructor(
        @ColumnInfo(name = Const.DB.COLUMN_CITY_NAME_HISTORY)
        var cityName: String = "",
        @ColumnInfo(name = Const.DB.COLUMN_DATE_HISTORY)
        var date: String = "",
        @ColumnInfo(name = Const.DB.COLUMN_TEMPERATURE_HISTORY)
        var temp: Double = 0.0) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Const.DB.COLUMN_ID_HISTORY)
    var id: Long = 0
}