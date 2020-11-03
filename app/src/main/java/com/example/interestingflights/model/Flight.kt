package com.example.interestingflights.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Flight(
    val cityFrom: String,
    val cityTo: String,
    val mapIdto: String,
    @SerializedName("fly_duration") val flyDuration: String,
    @SerializedName("price") private val _price: Int,
    val dTime: Date,
    val aTime: Date,
    val availability: Availability
) {
    val price: String
    get() = _price.toString() + "€"
}

data class Availability(val seats: Int?)