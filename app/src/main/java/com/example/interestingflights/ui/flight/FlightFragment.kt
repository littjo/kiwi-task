package com.example.interestingflights.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.interestingflights.R
import com.example.interestingflights.model.Flight
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_flight.*
import java.text.SimpleDateFormat

class FlightFragment(var flight: Flight) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        destinationTextView.text = flight.cityTo
        priceTextView.text = flight.price
        Picasso.get().load("https://images.kiwi.com/photos/600x330/" + flight.mapIdto + ".jpg").fit().centerCrop().into(
            imageView
        )
        val dateFormat = SimpleDateFormat("dd.MM.yyyy. HH:mm")
        departureTextView.text = dateFormat.format(flight.dTime)
        arivalTextView.text = dateFormat.format(flight.aTime)
        durationTextView.text = flight.flyDuration

        flight.availability.seats?.let { seatsTextView.text = it.toString() } ?: kotlin.run { seatsTextView.text = "0" }

    }
}