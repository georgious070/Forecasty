package com.android.forecasty.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.android.forecasty.Constants

@Entity(tableName = Constants.HISTORY_TABLE_NAME)
data class HistoryEntity constructor(
        @ColumnInfo(name = Constants.COLUMN_CITY_NAME_HISTORY)
        var cityName: String,
        @ColumnInfo(name = Constants.COLUMN_DATE_HISTORY)
        var date: String,
        @ColumnInfo(name = Constants.COLUMN_TEMPERATURE_HISTORY)
        var temp: Double) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.COLUMN_ID_HISTORY)
    var id: Long = 0
}