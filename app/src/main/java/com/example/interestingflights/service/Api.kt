package com.example.interestingflights.service

import com.example.interestingflights.model.Flight

interface Api {
    suspend fun getFlights(): List<Flight>
}