package com.example.earthquakeinformation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>: Fragment() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = onCreateBinding(inflater)
        return binding.root
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}