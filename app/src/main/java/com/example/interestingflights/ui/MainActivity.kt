package com.example.interestingflights.ui

import android.os.Bundle
import com.example.interestingflights.R
import com.example.interestingflights.ui.flights.FlightsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, FlightsFragment(), FlightsFragment::class.simpleName)
            .commit()
    }
}