package com.android.forecasty.ui.home

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.android.forecasty.ui.base.BaseNavigator
import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.android.forecasty.ui.cities.LocationParamsData
import com.android.forecasty.ui.navigation.Screens

class CurrentTownNavigator(
        activity: FragmentActivity,
        containerId: Int) : BaseNavigator(activity, containerId) {

    override fun createActivityIntent(context: Context, screenKey: String?, data: Any?) =
            when (screenKey) {
                Screens.CITIES_CYCLE -> CitiesCycleActivity.getIntent(context, data as LocationParamsData )
                else -> super.createActivityIntent(context, screenKey, data)
            }
}