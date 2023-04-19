package com.example.earthquakeinformation.ui.earthquakeDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.databinding.FragmentEarthquakeDetailsBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.earthquakeDetails.viewmodel.EarthquakeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthquakeDetailsFragment: BaseFragment<FragmentEarthquakeDetailsBinding>() {

    private val viewModel: EarthquakeDetailsViewModel by viewModels()
    private val navArgs: EarthquakeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEarthquake(navArgs.id)
        setObservers()
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentEarthquakeDetailsBinding =
        FragmentEarthquakeDetailsBinding.inflate(inflater)

    private fun setObservers(){
        viewModel.earthquake.observe(viewLifecycleOwner) { earthquake ->
            if (earthquake == null) return@observe
            setupIU(earthquake)
        }
    }

    private fun setupIU(earthquake: Earthquake) {
        earthquake.run {
            binding.detailsTitle.text = title
            binding.detailsMagText.text = resources.getString(
                R.string.earthquake_magnitude, mag.toString()
            )
            binding.detailsLocationText.text = resources.getString(
                R.string.earthquake_location, place
            )
            binding.detailsTimeText.text = resources.getString(
                R.string.earthquake_time, time.toString()
            )
            binding.detailsStatusText.text = resources.getString(
                R.string.earthquake_status, status
            )
            binding.detailsLatitudeText.text = resources.getString(
                R.string.earthquake_latitude, coordinates?.latitude.toString()
            )
            binding.detailsLongitudeText.text = resources.getString(
                R.string.earthquake_longitude, coordinates?.longitude.toString()
            )
        }
    }
}