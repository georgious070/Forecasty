package com.android.forecasty.ui.home

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.R
import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nested_scroll_view_content_main.*
import javax.inject.Inject
import android.content.Intent
import android.provider.Settings
import androidx.net.toUri


class CurrentTownActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var townViewModel: CurrentTownViewModel
    lateinit var rxPermissions: RxPermissions
    var latitude: Int = 0
    var longitude: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.app.appComponent.inject(this)

        townViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CurrentTownViewModel::class.java)

        rxPermissions = RxPermissions(this)

        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { granted ->
                    if (granted) {
                        observeLiveData()
                        setButtonNextListener()
                    }
                }
    }

    fun observeLiveData() {
        townViewModel.getData()
                .observe(this, Observer { response ->
                    text_temp.text = response!!.temp
                    collapsing_toolbar.title = response.cityName
                    latitude = response.latitude
                    longitude = response.longitude
                })
    }

    fun setButtonNextListener() {
        button_next.setOnClickListener { _ ->
            startActivity(CitiesCycleActivity.getIntent(
                    this@CurrentTownActivity, latitude, longitude))
        }
    }
}
