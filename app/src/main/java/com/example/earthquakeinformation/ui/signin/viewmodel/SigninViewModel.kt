package com.example.earthquakeinformation.ui.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinformation.data.repository.authentication.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): ViewModel() {

    private val _isEmptyUserTable = MutableLiveData(false)
    val isEmptyUserTable: LiveData<Boolean>
        get() = _isEmptyUserTable

    fun countUser(){
        viewModelScope.launch {
            if (authenticationRepository.countUsers() == 0)
                _isEmptyUserTable.postValue(true)
        }
    }
}