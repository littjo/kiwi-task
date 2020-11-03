package com.example.interestingflights.service

import com.example.interestingflights.model.Flight
import com.example.interestingflights.service.response.FlightsResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

class ApiClient(retrofit: Retrofit) : Api {

    interface Service {
        @GET("/flights?v=3&sort=popularity&asc=0&locale=en&flyFrom=49.2-16.61-250km&to=anywhere&featureName=aggregateResults&typeFlight=oneway&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=5&partner=skypicker-android")
        suspend fun getFlights(
            @Query("dateFrom") fromDate: String,
            @Query("dateTo") dateTo: String
        ): FlightsResponse
    }

    private val service = retrofit.create(Service::class.java)

    override suspend fun getFlights(): List<Flight> {
        val dateFormater = SimpleDateFormat("dd/MM/yyyy")
        val today = Calendar.getInstance()
        val dayTo = Calendar.getInstance()
        dayTo.add(Calendar.DATE, 1)
        return withContext(IO) {
            service.getFlights(
                dateFormater.format(today.time),
                dateFormater.format(dayTo.time)
            ).toModel()
        }
    }
}