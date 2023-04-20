package com.example.earthquakeinformation.ui.earthquakeList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.databinding.FragmentEarthquakelistBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.earthquakeList.adapter.EarthQuakeListAdapter
import com.example.earthquakeinformation.ui.earthquakeList.viewmodel.EarthquakeListViewModel
import com.example.earthquakeinformation.ui.main.tools.setGone
import com.example.earthquakeinformation.ui.main.tools.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthQuakeListFragment: BaseFragment<FragmentEarthquakelistBinding>(),
    EarthQuakeListAdapter.EarthquakeListeners {

    private val navArgs: EarthQuakeListFragmentArgs by navArgs()
    private val viewModel: EarthquakeListViewModel by viewModels()
    private var adapter: EarthQuakeListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
        setupRecycler()
        viewModel.getDataFromAnySource(navArgs.startTime)
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentEarthquakelistBinding =
        FragmentEarthquakelistBinding.inflate(inflater)

    private fun setupRecycler(){
        adapter = EarthQuakeListAdapter(this)
        binding.earthquakeListRecyclerView.adapter = adapter
    }

    private fun setObservers(){
        viewModel.earthquakeList.observe(viewLifecycleOwner) { earthquakeList ->
            if (earthquakeList.isNullOrEmpty()) {
                binding.noDataLayout.root.setVisible()
                return@observe
            }
            binding.noDataLayout.root.setGone()
            adapter?.updateEarthquakeList(earthquakeList)
            viewModel.saveEarthquakeListInDatabase(earthquakeList)
        }
    }

    private fun setListeners(){
        binding.noDataLayout.noDataButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onSelectEarthquake(earthquake: Earthquake) {
        navigateToDetails(earthquake.id)
    }

    private fun navigateToDetails(id: String) {
        findNavController().navigate(
            EarthQuakeListFragmentDirections
                .actionEarthQuakeListFragmentToEarthquakeDetailsFragment(id)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}