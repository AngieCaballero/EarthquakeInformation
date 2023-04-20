package com.example.earthquakeinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.databinding.ActivityMainBinding
import com.example.earthquakeinformation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController
    private var onFragmentBackPressed: (() -> Boolean)? = null
    private var callSuperOnBackPressed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        setupNavControler()
    }

    private fun setupNavControler() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.navHostFragmentContainer.id
        ) as NavHostFragment
        navController = navHostFragment.navController
    }



    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)
}