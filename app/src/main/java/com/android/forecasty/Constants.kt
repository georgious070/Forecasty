package com.android.forecasty

object Constants {

    //request
    const val APPID_KEY = "c2d0ab73b883e8921cd7a80bfbbfc319"
    const val BASE_URL = "http://api.openweathermap.org/"

    //intent keys TownActivity -> CitiesInCycleActivity
    const val INTENT_KEY_LATITUDE = "latitude"
    const val INTENT_KEY_LONGITUDE = "longitude"

    //database info
    const val DATABASE_NAME = "weather_db"
    const val DATABASE_VERSION = 1
    const val HISTORY_TABLE_NAME = "history"
    const val COLUMN_TEMPERATURE_HISTORY = "temp"
    const val COLUMN_CITY_NAME_HISTORY = "city_name"
    const val COLUMN_DATE_HISTORY ="date"
    const val COLUMN_ID_HISTORY = "id"
}