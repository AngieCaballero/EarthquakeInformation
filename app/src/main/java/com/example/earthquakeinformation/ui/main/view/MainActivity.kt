package com.example.earthquakeinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import com.example.earthquakeinformation.databinding.ActivityMainBinding
import com.example.earthquakeinformation.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)
}