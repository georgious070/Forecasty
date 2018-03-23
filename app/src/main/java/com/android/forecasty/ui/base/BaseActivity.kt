package com.android.forecasty.ui.base

import android.support.v7.app.AppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    abstract val navigator: BaseNavigator

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}