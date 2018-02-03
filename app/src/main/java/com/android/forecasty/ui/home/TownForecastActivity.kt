package com.android.forecasty.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TownForecastActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var townViewModel: TownViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.app.appComponent.inject(this)

        townViewModel = ViewModelProviders.of(this, viewModelFactory).get(TownViewModel::class.java)
        townViewModel.getData().observe(this, Observer { response -> text_name.text = response })
    }
}
