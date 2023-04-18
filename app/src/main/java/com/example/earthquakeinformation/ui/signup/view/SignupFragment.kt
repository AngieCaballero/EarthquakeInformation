package com.example.earthquakeinformation.ui.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earthquakeinformation.databinding.FragmentSignupBinding
import com.example.earthquakeinformation.ui.base.BaseFragment

class SignupFragment: BaseFragment<FragmentSignupBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentSignupBinding =
        FragmentSignupBinding.inflate(inflater)



}