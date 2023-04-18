package com.example.earthquakeinformation.ui.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakeinformation.data.domain.User
import com.example.earthquakeinformation.data.repository.authentication.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

    fun isDataEmpty(
        name: String = "",
        password: String = "",
        confirmPassword: String = ""
    ): Boolean{
        return name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
    }

    fun isPasswordIncorrect(
        password: String,
        confirmPassword: String
    ): Boolean{
        return password != confirmPassword
    }

    fun saveUserData(name: String, password: String){
        viewModelScope.launch {
            repository.createUser(User(name, password))
        }
    }
}