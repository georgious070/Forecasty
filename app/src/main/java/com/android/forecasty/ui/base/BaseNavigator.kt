package com.android.forecasty.ui.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.android.SupportAppNavigator

open class BaseNavigator(
        activity: FragmentActivity,
        containerId: Int) : SupportAppNavigator(activity, containerId) {

    override fun createActivityIntent(context: Context, screenKey: String?, data: Any?): Intent {
        throw NullPointerException("No intent for $screenKey activity")
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        throw NullPointerException("No intent for $screenKey fragment")
    }
}