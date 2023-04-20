package com.example.earthquakeinformation.ui.earthquakeDetails.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.data.domain.Coordinates
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.databinding.FragmentEarthquakeDetailsBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.earthquakeDetails.viewmodel.EarthquakeDetailsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthquakeDetailsFragment:
    BaseFragment<FragmentEarthquakeDetailsBinding>(),
    OnMapReadyCallback {

    private val viewModel: EarthquakeDetailsViewModel by viewModels()
    private val navArgs: EarthquakeDetailsFragmentArgs by navArgs()

    private var mMap: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsGoogleMapView.onCreate(savedInstanceState)
        setupGoogleMap()
        viewModel.getEarthquake(navArgs.id)
        setObservers()
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentEarthquakeDetailsBinding =
        FragmentEarthquakeDetailsBinding.inflate(inflater)

    private fun setObservers(){
        viewModel.earthquake.observe(viewLifecycleOwner) { earthquake ->
            if (earthquake == null) return@observe
            Log.i("Earthquake", earthquake.toString())
            setupIU(earthquake)
            createMapMarker(earthquake.title ?: "", earthquake.coordinates)
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

    private fun setupGoogleMap(){
        binding.detailsGoogleMapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun createMapMarker(title: String, coordinates: Coordinates?){
        if (coordinates == null) return
        val marker = LatLng(coordinates?.latitude ?: 12.1656, -80.1749)
        mMap?.addMarker(
            MarkerOptions()
                .position(marker)
                .title(title))
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 10f))
    }

    override fun onResume() {
        super.onResume()
        binding.detailsGoogleMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.detailsGoogleMapView.onPause()
    }

    override fun onDestroyView() {
        mMap = null
        binding.detailsGoogleMapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.detailsGoogleMapView.onLowMemory()
    }
}