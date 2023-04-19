package com.example.earthquakeinformation.ui.earthquakeList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.earthquakeinformation.databinding.FragmentEarthquakelistBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.earthquakeList.viewmodel.EarthquakeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthQuakeListFragment: BaseFragment<FragmentEarthquakelistBinding>() {

    private val navArgs: EarthQuakeListFragmentArgs by navArgs()
    private val viewModel: EarthquakeListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        viewModel.getDataFromAnySource(navArgs.startTime)
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentEarthquakelistBinding =
        FragmentEarthquakelistBinding.inflate(inflater)

    private fun setListeners(){

    }

    private fun setObservers(){
        viewModel.earthquakeList.observe(viewLifecycleOwner){ earthquakeList ->
            if (earthquakeList.isNullOrEmpty()) return@observe
        }
    }
}