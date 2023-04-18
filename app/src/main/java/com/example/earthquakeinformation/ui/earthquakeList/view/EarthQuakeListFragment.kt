package com.example.earthquakeinformation.ui.earthquakeList.view

import android.view.LayoutInflater
import com.example.earthquakeinformation.databinding.FragmentEartquakelistBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthQuakeListFragment: BaseFragment<FragmentEartquakelistBinding>() {

    override fun onCreateBinding(inflater: LayoutInflater): FragmentEartquakelistBinding =
        FragmentEartquakelistBinding.inflate(inflater)


}