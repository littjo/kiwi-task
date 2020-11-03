package com.example.interestingflights.di

import com.example.interestingflights.ui.flights.FlightsBuilder
import com.example.interestingflights.ui.flights.FlightsFragment
import com.example.interestingflights.ui.flights.FlightsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(
        modules = [FlightsModule::class, FlightsBuilder::class])
    fun contributeFlightsFragment(): FlightsFragment

}