package com.example.earthquakeinformation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = onCreateBinding(layoutInflater).apply { setContentView(root) }
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}