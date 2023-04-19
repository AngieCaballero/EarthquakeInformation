package com.example.earthquakeinformation.ui.earthquakeDetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.data.repository.earthquake.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeDetailsViewModel @Inject constructor(
    private val repository: EarthquakeRepository
): ViewModel() {

    private val _earthquake = MutableLiveData<Earthquake?>()
    val earthquake: LiveData<Earthquake?>
        get() = _earthquake

    fun getEarthquake(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            val earthquakeItem = repository.getEarthquakeDetails(id)
            _earthquake.postValue(earthquakeItem)
        }
    }
}