package com.android.forecasty.ui.cities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.Const
import com.android.forecasty.R
import com.android.forecasty.ui.base.BaseActivity
import com.android.forecasty.ui.base.BaseNavigator
import com.android.forecasty.utils.recycler.cities.CityAdapter
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesCycleActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var citiesCycleViewModel: CitiesCycleViewModel

    companion object {
        fun getIntent(context: Context, locationParamsData: LocationParamsData) =
                Intent(context, CitiesCycleActivity::class.java).apply {
                    putExtra(Const.IntentKeys.INTENT_KEY_LATITUDE, locationParamsData.latitude)
                    putExtra(Const.IntentKeys.INTENT_KEY_LONGITUDE, locationParamsData.longitude)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val adapter = CityAdapter(ArrayList())
        recycler_view_cities.adapter = adapter

        citiesCycleViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CitiesCycleViewModel::class.java)

        citiesCycleViewModel.getData(
                intent.getIntExtra(Const.IntentKeys.INTENT_KEY_LATITUDE, 0),
                intent.getIntExtra(Const.IntentKeys.INTENT_KEY_LONGITUDE, 0))
                .observe(this, Observer { response ->
                    adapter.updateList(response!!)
                })
    }

    override val navigator: BaseNavigator = BaseNavigator(this, R.layout.activity_cities)
}
