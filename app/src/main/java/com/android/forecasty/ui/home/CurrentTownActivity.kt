package com.android.forecasty.ui.home

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.android.forecasty.App
import com.android.forecasty.R
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_town.*
import javax.inject.Inject
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.net.toUri
import com.android.forecasty.Const
import com.android.forecasty.ui.base.BaseActivity
import com.android.forecasty.ui.base.BaseNavigator
import com.android.forecasty.utils.recycler.town.TownAdapter
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation

class CurrentTownActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var locationRequest: LocationRequest
    @Inject
    lateinit var rxLocation: RxLocation

    private lateinit var townViewModel: CurrentTownViewModel
    private lateinit var rxPermissions: RxPermissions
    private lateinit var adapter: TownAdapter
    private var latitude: Int = 0
    private var longitude: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        App.app.appComponent.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Const.Location.ACCESS_LOCATION_CODE -> observeLiveData()
        }
    }

    override val navigator: BaseNavigator = CurrentTownNavigator(this, R.layout.activity_town)

    fun observeLiveData() {
        townViewModel.getData()
                .observe(this, Observer { response ->
                    adapter.updateList(response!!.drop(1).toMutableList())
                    collapsing_toolbar.title = response.component1().cityName
                    latitude = response.component1().latitude
                    longitude = response.component1().longitude
                })
        setButtonNextListener()
    }

    fun setButtonNextListener() {
        button_next.setOnClickListener { _ ->
            townViewModel.toNextActivity(latitude, longitude)
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
}
