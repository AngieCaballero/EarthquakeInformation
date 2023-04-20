package com.example.earthquakeinformation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.earthquakeinformation.data.repository.earthquake.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: EarthquakeRepository
): ViewModel() {

}