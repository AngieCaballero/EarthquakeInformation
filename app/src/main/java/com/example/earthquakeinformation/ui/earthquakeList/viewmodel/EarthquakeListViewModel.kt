package com.example.earthquakeinformation.ui.earthquakeList.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinformation.data.domain.Earthquake
import com.example.earthquakeinformation.data.repository.earthquake.EarthquakeRepository
import com.example.earthquakeinformation.ui.helpers.setFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EarthquakeListViewModel @Inject constructor(
    private val repository: EarthquakeRepository
): ViewModel() {

    private val _earthquakeList = MutableLiveData<List<Earthquake>?>()
    val earthquakeList: LiveData<List<Earthquake>?>
        get() = _earthquakeList

    fun getDataFromAnySource(startTime: String? = null) {
        when(startTime){
            null -> {
                getEarthquakeFromQueryHistory()
            }
            else -> {
                getEarthquakeListFromDate(startTime)
            }
        }
    }

    private fun getEarthquakeListFromDate(startTime: String) {
        val currentDate = Date().setFormat("YYYY-MM-dd")
        viewModelScope.launch {
            val earthquakeListResponse = repository.getEarthquakeListFromDate(startTime, currentDate)
            _earthquakeList.postValue(earthquakeListResponse)
        }
    }

    private fun getEarthquakeFromQueryHistory(){
        viewModelScope.launch(Dispatchers.IO){
            val earthquakeHistoryList = repository.getEarthquakeFromQueryHistory()
            _earthquakeList.postValue(earthquakeHistoryList)
        }
    }

    fun saveEarthquakeListInDatabase(earthquakesConsultedList: List<Earthquake>) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertEarthquakeInDatabase(earthquakesConsultedList)
        }
    }
}