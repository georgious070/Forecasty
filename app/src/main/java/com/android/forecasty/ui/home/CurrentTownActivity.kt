package com.android.forecasty.ui.home

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.android.forecasty.App
import com.android.forecasty.R
import com.android.forecasty.ui.cities.CitiesCycleActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class CurrentTownActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var townViewModel: CurrentTownViewModel
    val MY_PERMISSIONS_REQUEST_LOCATION = 1
    var latitude: Int = 0
    var longitude: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.app.appComponent.inject(this)

        townViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentTownViewModel::class.java)
        townViewModel.getData().observe(this, Observer { response ->
            text_temp.text = response!!.temp
            text_city_name.text = response.cityName
            latitude = response.latitude
            longitude = response.longitude
        })

        button_next.setOnClickListener { v ->
            startActivity(CitiesCycleActivity.getIntent(this@CurrentTownActivity, latitude, longitude))
        }
    }

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                }
                return
            }
        }
    }

}
