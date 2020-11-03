package com.example.interestingflights.ui.flights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interestingflights.model.Flight
import com.example.interestingflights.repository.ApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FlightsViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _flights = MutableLiveData<List<Flight>?>()
    val flights: LiveData<List<Flight>?>
        get() = _flights

    init {
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                apiRepository.getFlights()
            }.onSuccess { flights ->
                _flights.value = flights
                _isLoading.value = false
            }.onFailure {
                _flights.value = null
                _isLoading.value = false
            }
        }
    }

}