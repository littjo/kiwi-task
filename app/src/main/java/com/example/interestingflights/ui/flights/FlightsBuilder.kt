package com.example.interestingflights.ui.flights

import androidx.lifecycle.ViewModel
import com.example.interestingflights.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FlightsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(FlightsViewModel::class)
    fun bindFlightsViewModel(viewModel: FlightsViewModel): ViewModel

}