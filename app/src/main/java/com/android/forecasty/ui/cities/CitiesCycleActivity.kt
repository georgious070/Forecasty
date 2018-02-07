package com.android.forecasty.ui.cities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.Constants
import com.android.forecasty.R
import javax.inject.Inject

class CitiesCycleActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var citiesCycleViewModel: CitiesCycleViewModel

    companion object {
        fun getIntent(context: Context, latitude: Int, longitute: Int): Intent {
            val intent = Intent(context, CitiesCycleActivity::class.java)
            intent.putExtra(Constants.INTENT_KEY_LATITUDE, latitude)
            intent.putExtra(Constants.INTENT_KEY_LONGITUDE, longitute)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        App.app.appComponent.inject(this)

        citiesCycleViewModel = ViewModelProviders.of(this, viewModelFactory).get(CitiesCycleViewModel::class.java)
    }
}
