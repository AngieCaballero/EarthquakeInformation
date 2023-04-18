package com.example.earthquakeinformation.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.example.earthquakeinformation.databinding.FragmentHomeBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.helpers.setFormat
import com.example.earthquakeinformation.ui.home.viewmodel.HomeViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    var datePicker: MaterialDatePicker<Long>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .build()
        setListeners()
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)

    private fun setListeners(){
        binding.homeDateLayout.setStartIconOnClickListener {
            datePicker!!.show(parentFragmentManager, "date_picker");
        }

        datePicker?.addOnPositiveButtonClickListener { selection ->
            val date = Date(selection)
            binding.homeDateEditText.setText(date.setFormat("YYYY-MM-dd"))
        }

    }
}