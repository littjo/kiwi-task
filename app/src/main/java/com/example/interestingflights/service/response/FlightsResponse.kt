package com.example.interestingflights.service.response

import com.example.interestingflights.model.Flight
import com.google.gson.annotations.SerializedName

class FlightsResponse : DataResponse<List<Flight>>() {

    @SerializedName("data")
    var data: List<Flight>? = null

    override fun toModel(): List<Flight> = data ?: arrayListOf()
}