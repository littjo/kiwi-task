package com.example.interestingflights.repository

import com.example.interestingflights.model.Flight
import com.example.interestingflights.service.Api
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val api: Api
){

    suspend fun getFlights(): List<Flight> {
        return api.getFlights()
    }

}