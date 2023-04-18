package com.example.earthquakeinformation.ui.signin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earthquakeinformation.databinding.FragmentSigninBinding
import com.example.earthquakeinformation.ui.base.BaseFragment

class SigninFragment: BaseFragment<FragmentSigninBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentSigninBinding =
        FragmentSigninBinding.inflate(inflater)
}