package com.android.forecasty.ui.home

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.R
import com.android.forecasty.ui.cities.CitiesCycleActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.net.toUri
import com.android.forecasty.Const
import com.android.forecasty.utils.recycler.town.TownAdapter
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation

class CurrentTownActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var locationRequest: LocationRequest
    @Inject lateinit var rxLocation: RxLocation

    lateinit var townViewModel: CurrentTownViewModel
    lateinit var rxPermissions: RxPermissions
    lateinit var adapter: TownAdapter
    var latitude: Int = 0
    var longitude: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        App.app.appComponent.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TownAdapter(ArrayList())
        recycler_view_dates.isNestedScrollingEnabled = false
        recycler_view_dates.adapter = adapter

        townViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CurrentTownViewModel::class.java)

        rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { granted ->
                    if (granted) {
                        if (checkGps()) {
                            observeLiveData()
                        } else {
                            enableGps()
                        }
                    } else {
                        openPermissionSettings()
                    }
                }
    }

    fun observeLiveData() {
        townViewModel.getData()
                .observe(this, Observer { response ->
                    adapter.updateList(response!!)
                    collapsing_toolbar.title = response.get(0).cityName
                    latitude = response.get(0).latitude
                    longitude = response.get(0).longitude
                })
        setButtonNextListener()
    }

    fun setButtonNextListener() {
        button_next.setOnClickListener { _ ->
            startActivity(CitiesCycleActivity.getIntent(
                    this, latitude, longitude))
        }
    }

    fun checkGps(): Boolean {
        return (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
                .isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun openPermissionSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                "package:$packageName".toUri())
        startActivityForResult(intent, Const.Location.ACCESS_LOCATION_CODE)
    }

    fun enableGps() {
        rxLocation.settings().checkAndHandleResolution(locationRequest)
                .subscribe { isEnabled ->
                    if (isEnabled) {
                        observeLiveData()
                    } else {
                        Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Const.Location.ACCESS_LOCATION_CODE -> observeLiveData()
        }
    }
}
