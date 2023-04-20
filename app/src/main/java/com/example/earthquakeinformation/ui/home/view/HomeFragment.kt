package com.example.earthquakeinformation.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.databinding.FragmentHomeBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.helpers.setFormat
import com.example.earthquakeinformation.ui.home.viewmodel.HomeViewModel
import com.example.earthquakeinformation.ui.main.view.MainActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private var datePicker: MaterialDatePicker<Long>? = null
    private var dateSelectedInDatePicker: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDatePickerInstance()
        setListeners()
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)

    private fun setListeners(){
        binding.homeDateLayout.setStartIconOnClickListener {
            datePicker?.show(parentFragmentManager, DATE_PICKER_TAG);
        }

        datePicker?.addOnPositiveButtonClickListener { selection ->
            val date = Date(selection)
            dateSelectedInDatePicker = date.setFormat("YYYY-MM-dd")
            binding.homeDateEditText.setText(dateSelectedInDatePicker)
        }

        binding.homeMakeQueryButton.setOnClickListener {
            if (isDateSelected()) navigateToEarthquakeListFragment(dateSelectedInDatePicker)
            else showAlertDialog()
        }

        binding.homeShowLastQueryButton.setOnClickListener {
            navigateToEarthquakeListFragment()
        }
        onBackPressedListener()
    }

    private fun createDatePickerInstance() {
        datePicker = MaterialDatePicker
            .Builder
            .datePicker()
            .setTitleText(resources.getString(R.string.select_date))
            .setCalendarConstraints(getCalendarConstraints().build())
            .build()
    }

    private fun getCalendarConstraints(): CalendarConstraints.Builder {
        // Obtener la fecha actual y la fecha hace 5 años
        val currentMillis = MaterialDatePicker.todayInUtcMilliseconds()
        val fiveYearsAgoMillis = currentMillis - TimeUnit.DAYS.toMillis(5*365)

        return CalendarConstraints.Builder()
            .setStart(fiveYearsAgoMillis)
            .setEnd(currentMillis)
            .setValidator(DateValidatorPointBackward.now())
    }

    private fun navigateToEarthquakeListFragment(startTime: String? = null){
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToEarthQuakeListFragment(
                startTime
            )
        )
    }

    private fun isDateSelected(): Boolean = !dateSelectedInDatePicker.isNullOrEmpty()

    private fun showAlertDialog(){
        context?.let { context ->
            MaterialAlertDialogBuilder(context)
                .setMessage(
                    resources.getString(R.string.no_date_selected_alert_dialog_message)
                    )
                .setPositiveButton(
                        resources.getString(R.string.accept)
                    ) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun showYesOrNoAlertDialog(){
        context?.let { context ->
            MaterialAlertDialogBuilder(context)
                .setMessage(
                    resources.getString(R.string.confirm_quit_the_app_message)
                )
                .setPositiveButton(
                    resources.getString(R.string.yes)
                ) { dialog, _ -> dialog.dismiss()
                    (requireActivity() as MainActivity).finish()
                }
                .setNegativeButton(
                    resources.getString(R.string.no)
                ) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun onBackPressedListener(){
        val dispatcher = (requireActivity() as MainActivity).onBackPressedDispatcher

        dispatcher.addCallback(viewLifecycleOwner) {
            if (isEnabled) {
                isEnabled = false
                showYesOrNoAlertDialog()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        datePicker = null
    }

    companion object{
        private const val DATE_PICKER_TAG = "date_picker"
    }
}