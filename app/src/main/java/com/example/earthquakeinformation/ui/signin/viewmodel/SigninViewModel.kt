package com.example.earthquakeinformation.ui.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinformation.data.repository.authentication.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

    private val _isEmptyUserTable = MutableLiveData(false)
    val isEmptyUserTable: LiveData<Boolean>
        get() = _isEmptyUserTable

    private val _isUserRegistered = MutableLiveData<Boolean?>(null)
    val isUserRegistered: LiveData<Boolean?>
        get() = _isUserRegistered

    fun countUser(){
        viewModelScope.launch(Dispatchers.Default) {
            if (repository.countUsers() == 0)
                _isEmptyUserTable.postValue(true)
        }
    }

    fun authenticateUser(name: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            _isUserRegistered.postValue(repository.authenticateUser(name, password))
        }
    }
}