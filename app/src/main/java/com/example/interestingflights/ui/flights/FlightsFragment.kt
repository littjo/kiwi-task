package com.example.interestingflights.ui.flights

import android.app.AlertDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.interestingflights.R
import com.example.interestingflights.ui.flight.FlightFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.flights_fragment.*
import javax.inject.Inject


class FlightsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val flightsViewModel: FlightsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flights_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flightsViewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        flightsViewModel.flights.observe(requireActivity(), Observer {
            if (it != null) {
                val fragments = ArrayList<FlightFragment>()
                it.forEach { flight ->
                    fragments.add(FlightFragment(flight))
                }
                viewPager.adapter = FlightsFragmentPagerAdapter(requireActivity(), fragments)
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    viewPager.setCurrentItem(tab.position, true)
                }.attach()
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Failed to get flights")
                    .setMessage("App will close now.")
                    .setPositiveButton(android.R.string.yes,
                        { dialog, which ->
                            requireActivity().finish()
                        })
                    .show()
            }
        })
    }

    class FlightsFragmentPagerAdapter(
        fragmentActivity: FragmentActivity,
        private val mFragments: List<Fragment>
    ) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getItemCount(): Int {
            return mFragments.size
        }
    }
}